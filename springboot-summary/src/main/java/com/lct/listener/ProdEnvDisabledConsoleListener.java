package com.lct.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.util.StringUtils;

public class ProdEnvDisabledConsoleListener implements GenericApplicationListener {

    public static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 30;

    public static final String APPENDER_NAME = "CONSOLE";

    public static final String PROD_ENV = "prod";

    private static final Class<?>[] EVENT_TYPES = {ApplicationEnvironmentPreparedEvent.class };

    @Override
    public boolean supportsEventType(ResolvableType eventType) {
        return isAssignableFrom(eventType.getRawClass(),EVENT_TYPES);
    }

    private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes) {
        if (type != null) {
            for (Class<?> supportedType : supportedTypes) {
                if (supportedType.isAssignableFrom(type)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent = (ApplicationEnvironmentPreparedEvent) event;
            String activeProfile = applicationEnvironmentPreparedEvent.getEnvironment().getProperty(ConfigFileApplicationListener.ACTIVE_PROFILES_PROPERTY);
            ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
            if(!StringUtils.isEmpty(logger.getAppender(APPENDER_NAME)) &&  activeProfile.equals(PROD_ENV)){
                throw new RuntimeException("线上环境，请卸载ConsoleAppender");
            }
        }
    }
}
