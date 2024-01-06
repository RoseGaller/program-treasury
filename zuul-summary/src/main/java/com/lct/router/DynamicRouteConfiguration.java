package com.lct.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicRouteConfiguration {

    @Autowired
    private ZuulProperties zuulProperties;

    @Autowired
    private ServerProperties serverProperties;

    @Bean
    DynamicRouteLocator dynamicRouteLocator(){
        DynamicRouteLocator dynamicRouteLocator = new DynamicRouteLocator(serverProperties.getServlet().getContextPath(),zuulProperties);
        return dynamicRouteLocator;
    }
}
