package com.lct.study.bean;

import lombok.Data;


@Data
public class APIResponse<T>{

    //200:服务端正常处理请求
    //非200:请求没有到服务端，可能是网络出问题、网络超时，或者网络配置的问题
    private int code; //HTTP响应状态码

    //如果HTTP响应码是200，查看success
    //success 为 true 的情况下，才需要继续解析响应体中的 data 结构体
    private boolean success;

    private String message;

    private T data;

}
