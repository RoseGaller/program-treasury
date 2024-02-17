package com.lct.bean;

import org.springframework.context.ApplicationEvent;

public class ErrorApplicationEvent extends ApplicationEvent {

    public ErrorApplicationEvent(Object source) {
        super(source);
    }
}
