package com.lct.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.springframework.util.StringUtils;

public class SampleFilter extends TurboFilter {

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
        if(StringUtils.isEmpty(MDC.get(LogbackFilter.USER_KEY))){
            return FilterReply.NEUTRAL;
        }

        if(!StringUtils.isEmpty(MDC.get(LogbackFilter.USER_KEY))){
            return FilterReply.ACCEPT;
        }
        return FilterReply.NEUTRAL;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    String name;
}
