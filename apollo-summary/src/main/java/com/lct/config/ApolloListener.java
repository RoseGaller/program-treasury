package com.lct.config;

import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApolloListener implements ConfigChangeListener {

    @Override
    public void onChange(ConfigChangeEvent configChangeEvent) {
        log.info("配置变更：" + configChangeEvent.getNamespace() + " : " + configChangeEvent.changedKeys());
    }
}
