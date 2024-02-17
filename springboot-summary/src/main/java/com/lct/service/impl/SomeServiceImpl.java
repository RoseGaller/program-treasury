package com.lct.service.impl;

import com.lct.service.SomeService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class SomeServiceImpl implements SomeService {

    @Retryable(value = Exception.class,maxAttempts = 3,backoff = @Backoff(delay = 1000,multiplier = 1.5))
    @Override
    public void doSome() throws Exception {
        System.out.println("do some");
        throw  new Exception("测试重试");
    }
}
