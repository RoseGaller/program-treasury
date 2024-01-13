package com.lct.KeyGenerator;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

import java.util.concurrent.atomic.AtomicLong;

public class StaticIKeyGenerator implements IdentifierGenerator {

    private AtomicLong atomicLong = new AtomicLong();

    @Override
    public Long nextId(Object entity) {
        return atomicLong.incrementAndGet();
    }
}
