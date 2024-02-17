package com.lct.rpc;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcRequest implements Serializable {

    private Class<?> serviceInterface;
    private String method;
    private Class<?>[] parameterTypes;
    private Object[] arguments;

}
