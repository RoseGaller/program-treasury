package com.lct.filter;

import ch.qos.logback.classic.Level;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ch.qos.logback.classic.Logger;

import javax.servlet.http.HttpServletRequest;

@Component
    public class AuthFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -100;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        Logger logger =  (Logger)LoggerFactory.getLogger(AuthFilter.class);
        logger.setLevel(Level.INFO);
        RequestContext requestContext =  RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest =  requestContext.getRequest();
        String token =  httpServletRequest.getHeader("token");
        if(StringUtils.isBlank(token)){
            throw  new ZuulException("非法请求",333,"token为空");
//            requestContext.setSendZuulResponse(false);
//            requestContext.set("isSuccess", false);
//            requestContext.setResponseBody("非法请求【缺少token】");
//            requestContext.getResponse().setContentType("application/json; charset=utf-8");
        }
        return null;
    }
}
