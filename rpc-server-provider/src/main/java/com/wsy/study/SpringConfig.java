package com.wsy.study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author 王思洋
 * @version 1.0
 * @description
 * @date 2019-06-23 10:56
 */
@Configuration
@ComponentScan(basePackages = "com.wsy.study")
public class SpringConfig {

    @Bean(name = "newRpcServer")
    public NewRpcServer newRpcServer(){
        return new NewRpcServer(9090);
    }

}
