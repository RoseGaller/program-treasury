package com.lct.router;

import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DynamicRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private static  Map<String, ZuulProperties.ZuulRoute> stringZuulRouteMap = new HashMap<String, ZuulProperties.ZuulRoute>();

    public DynamicRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
    }


    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<String, ZuulProperties.ZuulRoute>();
        routesMap.putAll(super.locateRoutes());

        //动态加载路由信息，从数据库加载
        return  routesMap;
    }
}
