package com.lct.KeyGenerator;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;

public class StaticIKeyGenerator implements IKeyGenerator {
    @Override
    public String executeSql(String incrementerName) {
        System.out.println("11111111111");
        return "123";
    }
}
