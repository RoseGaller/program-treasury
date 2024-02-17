package com.lct.listener;

import com.lct.bean.ErrorApplicationEvent;
import com.lct.monitor.PrometheusCustomMonitor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PrometheusApplicationListener implements ApplicationListener {

    @Resource
    private PrometheusCustomMonitor prometheusCustomMonitor;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ErrorApplicationEvent){
            prometheusCustomMonitor.getErrorCount().increment();
        }
    }
}
