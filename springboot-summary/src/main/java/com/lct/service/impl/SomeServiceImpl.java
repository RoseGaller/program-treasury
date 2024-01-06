package com.lct.service.impl;

import com.lct.service.SomeService;
import org.springframework.stereotype.Service;

@Service
public class SomeServiceImpl implements SomeService {

    @Override
    public void doSome() {
        System.out.println("do some");
    }
}
