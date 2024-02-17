package com.lct.tps;

import com.alipay.remoting.InvokeCallback;
import com.alipay.remoting.Url;
import com.alipay.remoting.exception.RemotingException;
import com.alipay.remoting.rpc.RpcClient;
import com.lct.rpc.RpcRequest;
import com.lct.rpc.UserService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TcpTps {

    private static final int TRANSACTIONS = 10000; // 设定要执行的事务总数
    private static final int THREADS = 35; // 设定并发执行的线程数
    private static final AtomicInteger counter = new AtomicInteger(0); // 用于计数的事务计数器
    // 10 thread -> 2222
    // 20 thread -> 2373
    // 30 thread -> 2472
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis(); // 记录开始时间

        RpcClient client = new RpcClient();
        client.init();

        ExecutorService executor = Executors.newFixedThreadPool(THREADS); // 创建固定大小的线程池
        int testCount = 1;
        for(int j=0;j<testCount;j++){
            // 提交事务到线程池执行
            for (int i = 0; i < TRANSACTIONS; i++) {
                executor.submit(() -> {
                    // 模拟事务处理
                    try {
                        processTransaction(client);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            while(counter.get() != TRANSACTIONS){
                Thread.sleep(10);
            }
            long endTime = System.currentTimeMillis(); // 记录结束时间
            long duration = endTime - startTime; // 计算总执行时间（毫秒）

            int totalTransactions = counter.get(); // 获取总事务数

            // 计算TPS
            double tps = (double) totalTransactions / (duration / 1000.0); // 将毫秒转换为秒

            System.out.println("Total Transactions: " + totalTransactions);
            System.out.println("Duration: " + duration + " ms");
            System.out.println("TPS: " + tps);
            System.out.println("=============================================================");
            counter.set(0);
        }



        // 关闭线程池，并等待所有任务完成
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    private static void processTransaction(RpcClient client) throws NoSuchMethodException, RemotingException, InterruptedException {
        RpcRequest demoRequest = new RpcRequest();
        demoRequest.setMethod(UserService.class.getMethod("getUser",Long.class).getName());
        demoRequest.setServiceInterface(UserService.class);
        demoRequest.setParameterTypes(new Class[]{Long.class});
        demoRequest.setArguments(new Object[]{343L});
        //同步
//        Url url = new Url("127.0.0.1",8081);
//        url.setConnWarmup(true);
//        url.setConnNum(10);
//        Object ss =  client.invokeSync(url,demoRequest,10000);
        Object ss =  client.invokeSync("127.0.0.1:8081",demoRequest,100000);
//        client.invokeWithCallback("127.0.0.1:8081", demoRequest, new InvokeCallback() {
//            @Override
//            public void onResponse(Object o) {
//                counter.incrementAndGet();
//            }
//
//            @Override
//            public void onException(Throwable throwable) {
//                counter.incrementAndGet();
//            }
//
//            @Override
//            public Executor getExecutor() {
//                counter.incrementAndGet();
//                return null;
//            }
//        },1000);
//        System.out.println(ss.toString());
        counter.incrementAndGet();
    }
}
