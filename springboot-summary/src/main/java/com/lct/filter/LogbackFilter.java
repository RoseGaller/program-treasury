package com.lct.filter;

import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LogbackFilter implements Filter {

    public static final String USER_KEY = "username";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader("token");
        MDC.put(USER_KEY,token);
        filterChain.doFilter(servletRequest,servletResponse);
        MDC.remove(USER_KEY);
    }
}
