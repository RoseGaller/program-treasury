package com.lct.layouts;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

//日志级别只打印第一个字母
public class SampleConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {

        return event.getLevel().toString().substring(0,1);
    }
}
