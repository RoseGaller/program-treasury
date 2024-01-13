package com.lct.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

//检测生产环境是否存在mock信息
@Component
public class CheckIsExistMockInfoProcessor implements BeanFactoryPostProcessor,EnvironmentAware {

    private Environment environment;

    //预防线上环境使用 Mock 服务
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        if(environment.getProperty(ConfigFileApplicationListener.ACTIVE_PROFILES_PROPERTY).equals("prod") &&
                !StringUtils.isEmpty(environment.getProperty("mock.host"))){
            throw new RuntimeException("生产环境mock.host不为空，请检查配置信息");
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
