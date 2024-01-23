package com.lct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

//https://logback.qos.ch/index.html
//TRACE<DEBUG<INFO<WARN<ERROR
public class HelloWorld extends SampleBase{

    public static void main(String[] args) throws InterruptedException {
        //默认加载logback.xml
        Logger logger = LoggerFactory.getLogger(HelloWorld.class);
        Thread.sleep(1000);
        MDC.put("userid","admin");
        //配置level为debug，trace不会打印
        logger.trace("tom:Hello world.");
        logger.debug("Hello world.");
        logger.debug("tom:Hello world.");
        logger.info("tom:Hello world.");
        logger.error("tom:Hello world.");
        MDC.put("userid","normal");
        logger.error("welcome,{}","Alice");
    }
}
