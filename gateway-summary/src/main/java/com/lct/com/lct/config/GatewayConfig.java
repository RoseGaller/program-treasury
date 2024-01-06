package com.lct.com.lct.config;

import com.lct.com.lct.filter.CustomGlobalFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    GlobalFilter customGlobalFilter(){
        CustomGlobalFilter customGlobalFilter = new CustomGlobalFilter();
        return  customGlobalFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeBuilder){
        return routeBuilder.routes()
                .route("test1", r -> {
                    return r.host("*.somehost.org").and().path("/somepath")
                            .filters(f -> f.addRequestHeader("header1", "header-value-1"))
                            .uri("http://someuri");

                })
                .build();
    }
}
