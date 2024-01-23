package com.lct.com.lct.config;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Configuration {

    public static void main(String[] args) {
        //-DLOG_DIR=/var/log/application system properties
        Logger logger = (Logger) LoggerFactory.getLogger(Configuration.class);
        LoggerContext loggerContext =  logger.getLoggerContext();
        //获取logback.xml中的属性信息
        Map<String, String> propertiesMap = loggerContext.getCopyOfPropertyMap();
        for(Map.Entry<String,String> entry:propertiesMap.entrySet()){
            logger.debug(entry.getKey() + " : " + entry.getValue());
        }
        //事先从xml中配置logger
        Logger loggerFromXml = (Logger) LoggerFactory.getLogger("file1");
        loggerFromXml.debug("success");
    }
}
