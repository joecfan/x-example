package com.example.grayrelease.custmribbon.rule;


import com.baw.grayrelease.custmribbon.predicate.NacosMetadataAwarePredicate;

public class NacosMetadataAwareRule extends DiscoveryEnabledRule {

    public NacosMetadataAwareRule() {
        super(new NacosMetadataAwarePredicate());
    }
}