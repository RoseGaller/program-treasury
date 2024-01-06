package com.lct.study.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

//自定义健康检查
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    private boolean status = true;

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if (status){
            builder.up();
        }else{
            builder.down();
        }
    }
}
