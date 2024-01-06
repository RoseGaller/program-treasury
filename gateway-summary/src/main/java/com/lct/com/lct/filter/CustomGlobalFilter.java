package com.lct.com.lct.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.lct.study.bean.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class CustomGlobalFilter implements GlobalFilter, Ordered {
    private Logger logger = LoggerFactory.getLogger(CustomGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("custom global filter");
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        if(!serverHttpRequest.getHeaders().containsKey("token")){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            logger.error("无效请求，未携带token");
            String data = "Request be denied!";
            //            DataBuffer dataBuffer =  response.bufferFactory().wrap(data.getBytes());
            ObjectMapper objectMapper = new ObjectMapper();
            APIResponse<String> apiResponse = new APIResponse<String>();
            apiResponse.setCode(200);
            apiResponse.setSuccess(false);
            apiResponse.setMessage(data);
            try {
                DataBuffer dataBuffer =  response.bufferFactory().wrap(objectMapper.writeValueAsBytes(apiResponse));
                return response.writeWith(Mono.just(dataBuffer));
            } catch (JsonProcessingException e) {
                apiResponse.setSuccess(false);
                apiResponse.setMessage(data);
                return response.writeWith(Mono.just(response.bufferFactory().wrap("序列化异常".getBytes())));
            }
        }
        return chain.filter(exchange); //直接放行
    }

    //数值越小，优先级越高
    @Override
    public int getOrder() {
        return -1;
    }
}
