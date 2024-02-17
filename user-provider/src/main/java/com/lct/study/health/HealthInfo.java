package com.lct.study.health;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
public class HealthInfo implements Serializable {

    private int errorCount;
    private int timeoutCount;

    private int timeoutThreshold = 2;
    private int errorThreshold = 5;

    private AtomicInteger  totalCall = new AtomicInteger();
    private AtomicInteger  totalError = new AtomicInteger();
    private AtomicInteger  totalElapsedTime = new AtomicInteger();

    private int avgTime;
    private String errorMessage;

}
