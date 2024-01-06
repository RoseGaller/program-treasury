package com.lct.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class SampleFilter extends Filter<ILoggingEvent> {
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    String name;
    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {

        if(iLoggingEvent.getLoggerName().contains("lct")){
            if(MDC.get(LogbackFilter.USER_KEY).equals(name) && iLoggingEvent.getLevel().equals(Level.DEBUG)){
                return FilterReply.ACCEPT;
            }
            return FilterReply.DENY;
        }
        return FilterReply.ACCEPT;
    }
}
