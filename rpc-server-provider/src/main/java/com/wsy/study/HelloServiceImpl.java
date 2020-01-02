package com.wsy.study;

import org.springframework.stereotype.Component;

/**
 * @author 王思洋
 * @version 1.0
 * @date 2019-06-11 11:52
 */
@RpcServer(value = HelloService.class,version = "v1.0")
public class HelloServiceImpl implements HelloService{

    @Override
    public String sayHello(String content) {
        System.out.println("[v1.0] sayHello :"+content);
        return "[v1.0] sayHello :"+content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("[v1.0] say user :" + user);
        return user+ "[v1.0] success";
    }
}
