package com.example.grayrelease.custmribbon.interceptor;

import com.baw.grayrelease.custmribbon.constant.LoadBalancerConstants;
import com.baw.grayrelease.custmribbon.support.RibbonFilterContext;
import com.baw.grayrelease.custmribbon.support.RibbonFilterContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

//@Component
public class HttpRequestInterceptor implements HandlerInterceptor {

    /*@Value("${spring.cloud.nacos.discovery.metadata.version:0.0}")
    private String metadataVersion;*/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Enumeration<String> headers = request.getHeaders(LoadBalancerConstants.VERSION);
        if (headers.hasMoreElements()) {
            RibbonFilterContext currentContext = RibbonFilterContextHolder.getCurrentContext();
            currentContext.getAttributes().put(LoadBalancerConstants.VERSION, headers.nextElement());
        }
        //System.out.println(metadataVersion);
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        RibbonFilterContextHolder.clearCurrentContext();
    }
}