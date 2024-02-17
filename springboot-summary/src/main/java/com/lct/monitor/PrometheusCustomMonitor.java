package com.lct.monitor;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class PrometheusCustomMonitor {

    @Autowired
    private MeterRegistry registry;

    private Counter errorCount;

    @PostConstruct
    private void init(){
        errorCount =  registry.counter("error_count","instanceId","127.0.0.1:9888");
    }

    public Counter getErrorCount(){
        return errorCount;
    }

}
