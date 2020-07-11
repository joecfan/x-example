package com.example.grayrelease.custmribbon.interceptor;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;

public class CustomHystrixContext {

//    private static final CustomHystrixContext context = new CustomHystrixContext();

    private static final HystrixRequestVariableDefault<String> versionVariable = new HystrixRequestVariableDefault<>();

//    public static CustomHystrixContext getInstance() {
//        return context;
//    }

    public static HystrixRequestVariableDefault<String> getInstance() {
        if (!HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.initializeContext();
        }
        return versionVariable;
    }

}