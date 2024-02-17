package com.lct.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test")
    public String test() throws InterruptedException {
        Thread.sleep((long) (Math.random()*1000));
        System.out.println("1111");
        return "rest";
    }
}
