package com.lct.layouts;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.util.ContextInitializer;
import org.slf4j.LoggerFactory;

public class SampleLayouts {

    public static void main(String[] args) {
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY,"logback-layouts.xml");
        Logger logger = (Logger) LoggerFactory.getLogger(SampleLayouts.class);
        try{
            int a = 1000 / 0;
        }catch (Exception e){
            logger.error("除数不能为空",e);
        }

    }
}
