package com.example.grayrelease.custmribbon.interceptor;

import com.baw.grayrelease.custmribbon.constant.LoadBalancerConstants;
import com.baw.grayrelease.custmribbon.support.RibbonFilterContext;
import com.baw.grayrelease.custmribbon.support.RibbonFilterContextHolder;
import feign.RequestInterceptor;
import org.apache.commons.lang3.StringUtils;

public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(feign.RequestTemplate template) {
        RibbonFilterContext currentContext = RibbonFilterContextHolder.getCurrentContext();
      
        String version = currentContext.getAttributes().get(LoadBalancerConstants.VERSION);
        if (StringUtils.isNotBlank(version)) {
            template.header(LoadBalancerConstants.VERSION, version);
    
        }
    }
}