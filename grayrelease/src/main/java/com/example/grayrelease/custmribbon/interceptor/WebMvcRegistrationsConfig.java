package com.example.grayrelease.custmribbon.interceptor;

import com.baw.grayrelease.apiversion.ApiVersioningRequestMappingHandlerMapping;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author: zhuhui bao
 * @date: 19:40 2019/9/9
 **/
@Configuration
public class WebMvcRegistrationsConfig extends WebMvcConfigurationSupport {

    @Override
    public RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new ApiVersioningRequestMappingHandlerMapping();
    }
}
