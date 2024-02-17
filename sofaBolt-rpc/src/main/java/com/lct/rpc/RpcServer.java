package com.lct.rpc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcServer {

    public static void main(String[] args) {
        System.setProperty("com.alipay.remoting.client.log.level","DEBUG");
        com.alipay.remoting.rpc.RpcServer rpcServer = new com.alipay.remoting.rpc.RpcServer(8081);
        //注册请求处理器（com.lct.rpc.RpcRequest -> DemoRequestProcessor）
        rpcServer.registerUserProcessor(new RequestProcessor());
        rpcServer.start();
        log.info("Sofa-Server启动成功");
    }
}
