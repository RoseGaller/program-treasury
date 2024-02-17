package com.lct.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * 自定义rule 全局使用，即所有服务共享除非服务有自定义的Configuration
 *
 */
@Component
public class CustomRule extends AbstractLoadBalancerRule {


    @Override
    public Server choose(Object key) {
        List<Server> serverList = this.getLoadBalancer().getReachableServers();
        return serverList.get(0);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
    }
}
