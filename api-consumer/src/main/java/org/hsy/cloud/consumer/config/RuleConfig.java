package org.hsy.cloud.consumer.config;

import com.netflix.loadbalancer.IRule;
import org.hsy.cloud.consumer.loadbalance.VersionRule;
import org.springframework.context.annotation.Bean;

public class RuleConfig {
    @Bean
    public IRule sameIpRule(){
        return new VersionRule();
    }
}
