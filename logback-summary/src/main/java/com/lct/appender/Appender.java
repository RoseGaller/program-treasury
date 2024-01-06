package com.lct.appender;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Appender {

    public static void main(String[] args) {
        //指定加载的配置文件
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY,"logback-MDC.xml");
        Logger logger = LoggerFactory.getLogger(Appender.class);
        logger.debug("Application started");
        MDC.put("userid", "Alice");
        logger.debug("Alice says hello");

        // 打印内部状态
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }
}
