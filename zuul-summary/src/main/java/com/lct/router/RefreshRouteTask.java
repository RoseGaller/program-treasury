package com.lct.router;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
public class RefreshRouteTask {

    @Autowired
    private DynamicRouteLocator dynamicRouteLocator;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Scheduled(fixedRate = 5000)
    private void refreshRoute() {
        log.info("定时刷新路由表");
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(dynamicRouteLocator);
        publisher.publishEvent(routesRefreshedEvent);
    }
}
