package com.example.ms2;

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
@ComponentScan("com.baw.microservice2")
public class Ms2Application {

    public static void main(String[] args) {
        SpringApplication.run(Ms2Application.class, args);
    }

}
