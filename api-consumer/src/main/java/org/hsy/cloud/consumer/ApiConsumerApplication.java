package org.hsy.cloud.consumer;

import org.hsy.cloud.consumer.config.RuleConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author heshiyuan
 * @date 2021/6/18 16:51
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RibbonClient(name = "producer", configuration = RuleConfig.class)
public class ApiConsumerApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApiConsumerApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
