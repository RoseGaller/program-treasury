package com.lct.utils;

import org.springframework.context.ApplicationContext;

public class SpringUtil {

    private static  ApplicationContext applicationContext = null;

    public static void setApplicationContext(ApplicationContext applicationContext){
        applicationContext = applicationContext;
    }

    public static Object get(String name){
        return applicationContext.getBean(name);
    }
}
