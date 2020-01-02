package com.wsy.study;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 王思洋
 * @version 1.0
 * @description
 * @date 2019-06-23 10:39
 */
@Component
public class NewRpcServer implements ApplicationContextAware, InitializingBean {

    ExecutorService executorService = Executors.newCachedThreadPool();

    private Map<String,Object> handleMap  = new HashMap<>();

    private int port;

    public NewRpcServer(int port) {
        this.port = port;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serverMap = applicationContext.getBeansWithAnnotation(RpcServer.class);
        if(!serverMap.isEmpty()){
            for (Object valueBean:serverMap.values()){

                RpcServer rpcServer = valueBean.getClass().getAnnotation(RpcServer.class);

                //拿到接口定义类
                String beanName = rpcServer.value().getName();

                //拿到版本号
                String version = rpcServer.version();

                //如果有版本号，版版本拼装给beanName
                if (!StringUtils.isEmpty(version)){
                    beanName += "-"+version;
                }

                handleMap.put(beanName,valueBean);

            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            //不断接受请求
            while (true){
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(handleMap,socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
