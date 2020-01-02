package com.wsy.study;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @author 王思洋
 * @version 1.0
 * @date 2019-06-11 16:13
 */
public class ProcessorHandler implements Runnable{

    private Socket socket;

    private Map<String , Object> handleMap;


    public ProcessorHandler(Map<String , Object> handleMap, Socket socket) {
        this.socket = socket;
        this.handleMap = handleMap;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            //从输入流中取出参数，类，方法
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();

            Object result=invoke(rpcRequest); //反射调用本地服务

            objectOutputStream=new ObjectOutputStream(socket.getOutputStream());

            //写入返回值
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();

        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            //关流
            if(objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String serviceName = rpcRequest.getClassName();

        String version = rpcRequest.getVersion();

        //如果有版本号，版版本拼装给serviceName
        if (!StringUtils.isEmpty(version)){
            serviceName += "-"+version;
        }

        Object service = handleMap.get(serviceName);//拿到类

        if (service == null){
            throw new RuntimeException(" service not found :"+serviceName);
        }

        //反射调用
        Object [] args = rpcRequest.getPatameters();
        Class<?> [] types = new Class[args.length];
        for (int i = 0; i < args.length ; i++) {
            types[i] = args[i].getClass();
        }

        Class Clazz = Class.forName(rpcRequest.getClassName());//helloServiceImpl.class
        Method method = Clazz.getMethod(rpcRequest.getMethodName(), types);//sayHello() saveUser()

        //返回值invoke
        Object invoke = method.invoke(service, args);//service -> helloServiceImpl
        return invoke;
    }

}
