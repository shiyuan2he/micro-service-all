package org.hsy.netflix.openfeign;

import org.hsy.cloud.netflix.custom.config.RuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author heshiyuan
 * @date 2021/6/26 19:05
 */
@SpringBootApplication
@EnableFeignClients
@RibbonClient(name = "ProducerApplication", configuration = RuleConfig.class)
public class OpenFeignApplication {
    public static void main(String[] args) {
        new SpringApplication(OpenFeignApplication.class).run(args);
    }
}
