package org.hsy.cloud.register;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author heshiyuan
 * @date 2021/6/18 11:12
 */
@SpringBootApplication
@EnableEurekaServer
public class NetflixEurekaApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(NetflixEurekaApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
