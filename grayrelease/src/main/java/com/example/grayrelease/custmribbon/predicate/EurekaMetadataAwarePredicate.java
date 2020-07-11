package com.example.grayrelease.custmribbon.predicate;

import com.baw.grayrelease.custmribbon.robin.EurekaWeightRoundRobin;
import com.baw.grayrelease.custmribbon.robin.WeightRoundRobin;
import com.baw.grayrelease.custmribbon.support.RibbonFilterContext;
import com.baw.grayrelease.custmribbon.support.RibbonFilterContextHolder;
import com.google.common.base.Optional;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EurekaMetadataAwarePredicate extends DiscoveryEnabledPredicate {
    
    WeightRoundRobin weightRoundRobin = new EurekaWeightRoundRobin();
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean apply(Server server) {
 
        final RibbonFilterContext context = RibbonFilterContextHolder.getCurrentContext();
        final Set<Map.Entry<String, String>> attributes = Collections.unmodifiableSet(context.getAttributes().entrySet());
        final Map<String, String> metadata = ((DiscoveryEnabledServer)server).getInstanceInfo().getMetadata();
        return metadata.entrySet().containsAll(attributes);
    }
    
    @Override
    public Optional<Server> chooseRoundRobinAfterFiltering(List<Server> servers, Object loadBalancerKey) {
        List<Server> eligible = getEligibleServers(servers, loadBalancerKey);
        if (eligible.size() == 0) {
            return Optional.absent();
        }
        return Optional.of(weightRoundRobin.choose(eligible));
    }
}