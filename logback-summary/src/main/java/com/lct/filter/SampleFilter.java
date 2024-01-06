package com.lct.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.lct.mdc.SimpleMDC;
import org.slf4j.LoggerFactory;

//自定义过滤器，继承Filter，此种类型的Filter只属于一个Appender
//在ILoggingEvent创建之后，执行appender中绑定的filter的decide方法
//当有多个filter时，只要前面的filter返回DENY或者ACCEPT，后面的filter不再执行
public class SampleFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) { //判断是否打印ILoggingEvent

        if(iLoggingEvent.getMessage().contains("sample")){
            return FilterReply.ACCEPT; //只有message含有sample才会打印
//            return FilterReply.NEUTRAL;
        }
        return FilterReply.DENY;
    }

    public static void main(String[] args) {
        //指定加载的配置文件
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY,"logback-filter.xml");
        Logger logger = (Logger) LoggerFactory.getLogger(SampleFilter.class);
//        logger.debug("Application started");
//        logger.debug("print sample");
//        logger.info("print sample");
//        logger.debug("Application end");

        logger.debug("Application started");
        logger.info("print billing");
        logger.debug("Application end");
    }
}
