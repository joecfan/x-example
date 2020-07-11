package com.example.grayrelease.custmribbon.rule;


import com.baw.grayrelease.custmribbon.predicate.EurekaMetadataAwarePredicate;

public class EurekaMetadataAwareRule extends DiscoveryEnabledRule {

    public EurekaMetadataAwareRule() {
        super(new EurekaMetadataAwarePredicate());
    }
    
    
}