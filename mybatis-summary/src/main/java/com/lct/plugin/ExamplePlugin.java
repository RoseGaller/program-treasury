package com.lct.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

@Intercepts({@Signature(type= StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class})})
public class ExamplePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("对方法进行了增强....");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("将要包装的目标对象:"+target);
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置的初始化参数:"+properties );
    }
}
