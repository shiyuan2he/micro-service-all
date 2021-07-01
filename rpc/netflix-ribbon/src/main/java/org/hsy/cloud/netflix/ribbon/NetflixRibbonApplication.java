package org.hsy.cloud.netflix.ribbon;

import org.hsy.cloud.netflix.custom.config.RuleConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author heshiyuan
 * @date 2021/6/25 11:50
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "producer", configuration = RuleConfig.class)
public class NetflixRibbonApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(NetflixRibbonApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
