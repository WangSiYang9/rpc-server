package com.wsy.study;

/**
 * @author 王思洋
 * @version 1.0
 * @date 2019-06-11 11:52
 */
@RpcServer(value = HelloService.class,version = "v2.0")
public class HelloServiceImpl2 implements HelloService{

    @Override
    public String sayHello(String content) {
        System.out.println("[v2.0] sayHello :"+content);
        return "[v2.0] sayHello :"+content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("[v2.0] say user :" + user);
        return user+ "[v2.0] success";
    }
}
