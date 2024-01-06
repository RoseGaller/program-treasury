package com.lct.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebMvcConfiguration extends WebMvcConfigurationSupport {

    //在spring boot中使用Filter和HandlerIntercepter中对于相同路径匹配写法有差别,
    //如路径/api/v1/service1,在Filter中可以使用/api/*,
    //但在HandlerIntercepter注册时需要写成/api/**
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        SomeInterceptor someInterceptor = new SomeInterceptor();
        registry.addInterceptor(someInterceptor)
                .addPathPatterns("/**")//该方法用于指定拦截路径，例如拦截路径为“/**”，表示拦截所有请求，包括对静态资源的请求
                .excludePathPatterns("/test/v1");//该方法用于排除拦截路径
    }
}
