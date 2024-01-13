package com.lct;

import com.ctrip.framework.apollo.spring.config.ConfigPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.*;

//检测是否存在重复的配置信息
@Component
@Slf4j
public class CheckIsExistDuplicateInfoProcessor implements BeanFactoryPostProcessor, EnvironmentAware {

    private Environment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Map<String, List<String>> propertiesNameToPropertiesSource = new HashMap<String, List<String>>();
        Set<String> sourceSet = new HashSet<>();
        StandardServletEnvironment standardServletEnvironment = (StandardServletEnvironment) this.environment;
        MutablePropertySources mutablePropertySources = standardServletEnvironment.getPropertySources();
        for (PropertySource<?> propertySources : mutablePropertySources) {
           String propertySourcesName =  propertySources.getName();
           if(sourceSet.contains(propertySourcesName)){
               continue;
           }
            sourceSet.add(propertySourcesName);
           if(propertySources instanceof MapPropertySource){
               MapPropertySource mapPropertySource = (MapPropertySource) propertySources;
               String[] propertyNames = mapPropertySource.getPropertyNames();
               if(propertyNames!= null && propertyNames.length >0){
                   for(String propertyName : propertyNames){
                       if(propertiesNameToPropertiesSource.containsKey(propertyName)){
                           propertiesNameToPropertiesSource.get(propertyName).add(propertySourcesName);
                           continue;
                       }
                       List<String> sources = new ArrayList<>();
                       sources.add(propertySourcesName);
                       propertiesNameToPropertiesSource.put(propertyName,sources);
                   }
               }
           }
           if(propertySources instanceof PropertySource.StubPropertySource){
               PropertySource.StubPropertySource stubPropertySource = (PropertySource.StubPropertySource) propertySources;
           }
           if(propertySources instanceof CompositePropertySource){

               CompositePropertySource compositePropertySource = (CompositePropertySource) propertySources;
               String[] propertyNames = compositePropertySource.getPropertyNames();
               if(propertyNames!= null && propertyNames.length >0){
                   for(String propertyName : propertyNames){
                       if(propertiesNameToPropertiesSource.containsKey(propertyName)){
                           propertiesNameToPropertiesSource.get(propertyName).add(propertySourcesName);
                           continue;
                       }
                       List<String> sources = new ArrayList<>();
                       sources.add(propertySourcesName);
                       propertiesNameToPropertiesSource.put(propertyName,sources);
                   }
               }
           }
           if(propertySources instanceof SimpleCommandLinePropertySource){
               SimpleCommandLinePropertySource commandLinePropertySource = (SimpleCommandLinePropertySource) propertySources;
               String[] propertyNames = commandLinePropertySource.getPropertyNames();
               if(propertyNames!= null && propertyNames.length >0){
                   for(String propertyName : propertyNames){
                       if(propertiesNameToPropertiesSource.containsKey(propertyName)){
                           propertiesNameToPropertiesSource.get(propertyName).add(propertySourcesName);
                           continue;
                       }
                       List<String> sources = new ArrayList<>();
                       sources.add(propertySourcesName);
                       propertiesNameToPropertiesSource.put(propertyName,sources);
                   }
               }
           }
        }
        for(Map.Entry<String,List<String>> entry : propertiesNameToPropertiesSource.entrySet()){
            StringBuilder stringBuilder  = new StringBuilder();
            if(entry.getValue().size() > 1){
                for(int i = 0;i< entry.getValue().size();i++){
                    stringBuilder.append(entry.getValue().get(i));
                    if(i != entry.getValue().size()-1){
                        stringBuilder.append(",");
                    }
                }
                log.error("存在重复配置,属性名称=[" + entry.getKey() + "},配置源 ="+ "{" +stringBuilder.toString()+ "}" );
            }

        }
        propertiesNameToPropertiesSource = null;
        sourceSet = null;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
