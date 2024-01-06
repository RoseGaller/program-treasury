package com.lct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(WrapService.class)
@EnableConfigurationProperties(WrapProperties.class)
public class WrapAutoConfigure {

    @Autowired
    private WrapProperties wrapProperties;

    @Bean
    @ConditionalOnProperty(prefix = "wrap",value = "enable",havingValue = "true")
    WrapService createWrapService(){
        WrapService wrapService = new WrapService(wrapProperties.getBefore(),wrapProperties.getAfter());
        return  wrapService;
    }
}
