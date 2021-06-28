package org.hsy.cloud.api.producer;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author heshiyuan
 * @date 2021/6/18 16:51
 */
@SpringBootApplication
@EnableEurekaClient
public class ApiProducerApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApiProducerApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
