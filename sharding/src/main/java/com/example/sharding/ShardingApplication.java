package com.example.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: zhuhui bao
 * @date: 19:49 2020/1/13
 * @desc：分库分表例子
 **/
@SpringBootApplication(exclude = {JtaAutoConfiguration.class})
@ComponentScan("com.example.sharding")
@MapperScan(basePackages = {"com.example.sharding.dao"})
public class ShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingApplication.class, args);
    }

}
