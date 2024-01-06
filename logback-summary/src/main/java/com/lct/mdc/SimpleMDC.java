package com.lct.mdc;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;

public class SimpleMDC {

    //Mapped Diagnostic Context 简称 MDC 映射诊断上下文

    //MDCInsertingServletFilter:向MDC添加ServletRequest属性信息
    //可自定义filter(javax.servlet.Filter)，向MDC添加信息，比如添加用户信息，将输入日志和用户绑定，但是要考虑跨线程的问题
    //MDC内部是使用ThreadLocal存放信息

    public static void main(String[] args) throws InterruptedException {
        //指定加载的配置文件
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY,"logback-MDC.xml");

        Logger logger = (Logger) LoggerFactory.getLogger(SimpleMDC.class);
        printMDCLog(logger,false);

        //跨线程使用MDC
        //深度拷贝，修改deepCopyMap，不会影响原线程绑定的map
        final Map<String, String> deepCopyMap = MDC.getCopyOfContextMap();
        Thread MDCThread = new Thread(new Runnable() {
            @Override
            public void run() {
                MDC.setContextMap(deepCopyMap);
                printMDCLog(logger,true);
            }
        });
        MDCThread.start();
        MDCThread.join();

        // 打印内部状态
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }

    private static void printMDCLog(Logger logger,boolean strideThread ) {
        MDC.put("first", "Dorothy");
        MDC.put("last", "Parker");
        logger.info("Check enclosed.");
        logger.debug("The most beautiful two words in English.");

        MDC.put("first", "Richard");
        MDC.put("last", "Nixon");
        logger.info("I am not a crook.");
        logger.info("Attributed to the former US president. 17 Nov 1973.");
        logger.info("============================= " + strideThread + " ===========================");
    }
}
