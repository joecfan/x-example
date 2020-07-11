package com.example.grayrelease.custmribbon.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author: zhuhui bao
 * @date: 19:32 2019/9/4
 **/
@Configuration
public class RequestConfig {

    @Autowired
    private RestTemplateInterceptor restTemplateInterceptor;

//    @Autowired
//    private HttpRequestInterceptor httpRequestInterceptor;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        //restTemplate.setInterceptors(Collections.singletonList(new RestTemplateInterceptor()));
        restTemplate.setInterceptors(Collections.singletonList(restTemplateInterceptor));

        return restTemplate;
    }

/*    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个
        registry.addInterceptor(httpRequestInterceptor).addPathPatterns("/**");
    }*/

    @Bean
    public CustomHystrixConcurrencyStrategy customHystrixConcurrencyStrategy() {

        return new CustomHystrixConcurrencyStrategy();
    }

}
