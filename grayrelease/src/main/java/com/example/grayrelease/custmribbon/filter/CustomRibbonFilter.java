package com.example.grayrelease.custmribbon.filter;

import com.baw.grayrelease.custmribbon.constant.LoadBalancerConstants;
import com.baw.grayrelease.custmribbon.support.RibbonFilterContext;
import com.baw.grayrelease.custmribbon.support.RibbonFilterContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: zhuhui bao
 * @date: 14:52 2020/1/17
 **/
public class CustomRibbonFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println(Thread.currentThread().getName());
        String version = exchange.getRequest().getHeaders().getFirst(LoadBalancerConstants.VERSION);
        if (StringUtils.isNotBlank(version)) {
            RibbonFilterContext currentContext = RibbonFilterContextHolder.getCurrentContext();
            currentContext.add(LoadBalancerConstants.VERSION, version);
        }
        Mono<Void> mono = chain.filter(exchange)
                .subscriberContext(ctx -> ctx.put(LoadBalancerConstants.VERSION, version))
                //reactor  对所有请求都正常处理完成后加一个响应参数，或者是打印日志。
                .doFinally(signal ->  RibbonFilterContextHolder.clearCurrentContext());
        return mono;

    }

    @Override
    public int getOrder() {
        return 10000;
    }

}
