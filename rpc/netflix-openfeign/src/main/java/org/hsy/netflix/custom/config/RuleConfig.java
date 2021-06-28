package org.hsy.netflix.custom.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 必须放到 OpenFeignApplication 根目录之外
 * @author heshiyuan
 * @date 2021/6/25 14:19
 */
@Configuration
public class RuleConfig {

    @Bean
    public IRule sameIpRule(){
//        return new IpSameRule();
//        return new IPWeightRule();
        return new RandomRule();
    }
}
