package com.lct.fill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

public class CustomMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("name","default",metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("111");
    }
}
