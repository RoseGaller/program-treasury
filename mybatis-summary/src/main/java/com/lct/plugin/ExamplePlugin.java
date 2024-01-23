package com.lct.plugin;

import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

@Intercepts({@Signature(type= Executor.class,method = "update",args = {MappedStatement.class,Object.class})})
public class ExamplePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        if(mappedStatement.getSqlCommandType().ordinal() == SqlCommandType.INSERT.ordinal()){
            System.out.println("插入数据：自动填充主键....");
            Class clazz = invocation.getArgs()[1].getClass();
            final List<Field> res = new LinkedList<>();
            ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
                @Override
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    res.add(field);
                }
            });
            for(Field field:res){
                TableId tableId = field.getAnnotation(TableId.class);
                if(tableId !=null){
                    field.setAccessible(true);
                    field.set(invocation.getArgs()[1],21233L);
                    break;
                }
            }
        }
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
