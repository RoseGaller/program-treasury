package com.lct.rpc;

import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.SyncUserProcessor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RequestProcessor extends SyncUserProcessor<RpcRequest> {

    private Map<Object,Object> map = new HashMap<>();

//    @Override
//    public Executor getExecutor() {
//        return Executors.newFixedThreadPool(100);
//    }

    public RequestProcessor(){
        map.put(UserService.class,new UserServiceImpl());
    }

    @Override
    public Object handleRequest(BizContext bizContext, RpcRequest demoRequest) throws Exception {
        Method method = demoRequest.getServiceInterface().getMethod(demoRequest.getMethod(),demoRequest.getParameterTypes());
        Object obj = method.invoke(map.get(demoRequest.getServiceInterface()),demoRequest.getArguments());
        System.out.println("执行");
        return obj;
    }

    @Override
    public String interest() {
        return "com.lct.rpc.RpcRequest";
    }
}
