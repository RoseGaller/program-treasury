package com.lct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//官网https://logback.qos.ch/index.html
public class HelloWorld extends SampleBase{

    public static void main(String[] args) {
        //默认加载logback.xml
        Logger logger = LoggerFactory.getLogger(HelloWorld.class);

        //配置level为debug，trace不会打印
        logger.trace("tom:Hello world.");
        logger.debug("Hello world.");
        logger.debug("tom:Hello world.");
        logger.info("tom:Hello world.");
        logger.error("tom:Hello world.");
    }
}
