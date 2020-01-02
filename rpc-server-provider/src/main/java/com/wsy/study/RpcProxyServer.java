package com.wsy.study;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 王思洋
 * @version 1.0
 * @date 2019-06-11 15:51
 */
public class RpcProxyServer {

    ExecutorService executorService = Executors.newCachedThreadPool();

//    public void pubisher(Object service,int port){
//
//        ServerSocket serverSocket = null;
//        try {
//            serverSocket = new ServerSocket(port);
//            //不断接受请求
//            while (true){
//                Socket socket = serverSocket.accept();
//                executorService.execute(new ProcessorHandler(service,socket));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        } finally {
//            if (serverSocket != null){
//                try {
//                    serverSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//
//    }

}
