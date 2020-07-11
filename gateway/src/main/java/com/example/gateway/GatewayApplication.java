package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

/**
 * @author: zhuhui bao
 * @date: 19:49 2020/1/15
 * @desc：网关例子例子（gateway）
 **/
@SpringBootApplication(exclude = {JtaAutoConfiguration.class})
//@ComponentScan("com.baw.gateway")
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
