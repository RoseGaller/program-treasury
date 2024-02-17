package com.lct.rpc;

import com.alipay.remoting.exception.RemotingException;
import com.alipay.remoting.rpc.RpcClient;

public class ClientDemo {
    public static void main(String[] args) throws RemotingException, InterruptedException, NoSuchMethodException {
        RpcClient client = new RpcClient();
        client.registerUserProcessor(new RequestProcessor());
//        SerializerManager.addSerializer(2,new StringSerializer());
//        System.setProperty("bolt.serializer","2");
        client.init();
        RpcRequest demoRequest = new RpcRequest();
        demoRequest.setMethod(UserService.class.getMethod("getUser",Long.class).getName());
        demoRequest.setServiceInterface(UserService.class);
        demoRequest.setParameterTypes(new Class[]{Long.class});
        demoRequest.setArguments(new Object[]{343L});
        Object ss =  client.invokeSync("127.0.0.1:8081",demoRequest,10000);
        System.out.println(ss.toString());
    }
}
