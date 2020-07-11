package com.example.grayrelease.custmribbon.interceptor;

import com.baw.grayrelease.custmribbon.constant.LoadBalancerConstants;
import com.baw.grayrelease.custmribbon.support.RibbonFilterContext;
import com.baw.grayrelease.custmribbon.support.RibbonFilterContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {


    @Value("${spring.cloud.nacos.discovery.metadata.version:0.0}")
    private String metadataVersion;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);

        HttpHeaders httpHeaders = requestWrapper.getHeaders();
        RibbonFilterContext currentContext = RibbonFilterContextHolder.getCurrentContext();
        String version = currentContext.getAttributes().get(LoadBalancerConstants.VERSION);

        if (StringUtils.isNotBlank(version)) {
            requestWrapper.getHeaders().add(LoadBalancerConstants.VERSION, version);
        }

        requestWrapper.getHeaders().add(LoadBalancerConstants.VERSION, metadataVersion);
        currentContext.add(LoadBalancerConstants.VERSION, metadataVersion);

        //
        String versionV = CustomHystrixContext.getInstance().get();
        //

        System.out.println(metadataVersion);

        return execution.execute(requestWrapper, body);
    }
}