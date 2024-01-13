package com.lct.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.netflix.ribbon.RibbonApplicationContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EagerLoadListener implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware {

    ApplicationContext applicationContext = null;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        RibbonApplicationContextInitializer initializer = (RibbonApplicationContextInitializer) applicationContext.getBean("ribbonApplicationContextInitializer");
        if(initializer != null ){
            log.info("eager-load.enabled配置 生效");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
