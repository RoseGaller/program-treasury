package com.lct.advice;

import com.lct.bean.APIResponse;
import com.lct.bean.ErrorApplicationEvent;
import com.lct.exception.APIException;
import com.lct.monitor.PrometheusCustomMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class APIResponseAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    private PrometheusCustomMonitor prometheusCustomMonitor;

    @Autowired
    private ApplicationEventPublisher publisher;

    @ExceptionHandler({APIException.class})
    public APIResponse handleApiException(HttpServletRequest request, APIException ex){
        //同步调用
//        prometheusCustomMonitor.getErrorCount().increment();
        //异步
        publisher.publishEvent(new ErrorApplicationEvent(prometheusCustomMonitor));

        APIResponse apiResponse = new APIResponse();
        apiResponse.setSuccess(false);
        apiResponse.setCode(ex.getErrorCode());
        apiResponse.setMessage(ex.getErrorMessage());
        return apiResponse;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.getParameterType() != APIResponse.class
                && methodParameter.getExecutable().getName().contains("lct")
                && AnnotationUtils.findAnnotation(methodParameter.getMethod(),NoAPIResponse.class) == null
                && AnnotationUtils.findAnnotation(methodParameter.getDeclaringClass(),NoAPIResponse.class) == null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("OK");
        apiResponse.setCode(2000);
        apiResponse.setData(body);
        return apiResponse;
    }
}
