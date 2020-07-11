package com.example.grayrelease.custmribbon.predicate;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.baw.grayrelease.custmribbon.interceptor.CustomHystrixContext;
import com.baw.grayrelease.custmribbon.robin.NacosWeightRoundRobin;
import com.baw.grayrelease.custmribbon.robin.WeightRoundRobin;
import com.baw.grayrelease.custmribbon.support.RibbonFilterContext;
import com.baw.grayrelease.custmribbon.support.RibbonFilterContextHolder;
import com.google.common.base.Optional;
import com.netflix.loadbalancer.Server;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NacosMetadataAwarePredicate extends DiscoveryEnabledPredicate {
    
    WeightRoundRobin weightRoundRobin = new NacosWeightRoundRobin();
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean apply(Server server) {
    
        System.out.println(Thread.currentThread().getName());

        String version = CustomHystrixContext.getInstance().get();
        final RibbonFilterContext context = RibbonFilterContextHolder.getCurrentContext();
        final Set<Map.Entry<String, String>> attributes = Collections.unmodifiableSet(context.getAttributes().entrySet());
        final Map<String, String> metadata = ((NacosServer) server).getMetadata();
        
        boolean b = metadata.entrySet().containsAll(attributes);
        if (!b) {
            System.out.println("不满足条件");
            System.out.println("-------- 需要的meta信息 --------");
            attributes.forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
            System.out.println("-------- 存在的meta信息 --------" + server.getPort());
            metadata.forEach((k, v) -> System.out.println(k + ":" + v));
        }
        return b;
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