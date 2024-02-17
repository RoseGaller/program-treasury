package com.lct.filter;

import com.lct.utils.SpringUtil;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter("/*")
@Getter
@Setter
public class LogbackFilter implements Filter {

    public static final String USER_KEY = "USERNAME";

    private ApplicationContext applicationContext = null;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader("token");
        LogDiagnose logDiagnose = (LogDiagnose) applicationContext.getBean("logDiagnose");
        if(logDiagnose.isEnabled() && !StringUtils.isEmpty(token) && logDiagnose.getUsers().contains(token)){
            MDC.put(USER_KEY,token);
            filterChain.doFilter(servletRequest,servletResponse);
            MDC.remove(USER_KEY);
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }


}
