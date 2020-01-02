package com.wsy.study;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

//        HelloService helloService = new HelloServiceImpl();
//
//        RpcProxyServer rpcProxyServer =  new RpcProxyServer();
//
//        rpcProxyServer.pubisher(helloService,9090);


        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ((AnnotationConfigApplicationContext)context).start();

    }
}
