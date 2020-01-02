package com.wsy.study;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 王思洋
 * @version 1.0
 * @description
 * @date 2019-06-22 12:30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcServer {

    //拿到服务接口
    Class <?> value();

    //版本号
    String version() default "";
}
