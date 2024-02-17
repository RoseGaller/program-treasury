package com.lct.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableApolloConfig("test-apollo")
public class ApolloConfig {

    @com.ctrip.framework.apollo.spring.annotation.ApolloConfig("test-apollo")
    private Config config;

    @Bean
    public ConfigChangeListener configChangeListener(){
        ApolloListener apolloListener = new ApolloListener();
        return  apolloListener;
    }

    @ApolloConfigChangeListener("test-apollo") //必须指定namespace
    private void someOnChange(ConfigChangeEvent changeEvent) {
        log.info(config.toString());
        log.info("配置变革：" + changeEvent.getNamespace() + "," + changeEvent.changedKeys());
    }

}
