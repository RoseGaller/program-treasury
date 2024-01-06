package com.lct.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

//TurboFilter属于全局，每次打印日志时都会调用
//在LoggingEvent创建之前，就会执行
public class SampleTurboFilter extends TurboFilter {

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
        if(format.contains(this.getName())){
            return FilterReply.DENY;
        }
        return FilterReply.ACCEPT;
    }

    public static void main(String[] args) {
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY,"logback-turboFilter.xml");
        Logger logger = (Logger) LoggerFactory.getLogger(SampleTurboFilter.class);
        logger.debug("Application started");
        logger.debug("print SampleTurboFilter");
        logger.debug("Application end");
    }
}
