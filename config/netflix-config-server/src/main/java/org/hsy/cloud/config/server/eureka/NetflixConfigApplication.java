package org.hsy.cloud.config.server.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



/**
 * @author heshiyuan
 * @date 2021/6/18 11:11
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class NetflixConfigApplication {
    public static void main(String[] args) {
        new SpringApplication(NetflixConfigApplication.class).run(args);
    }
}
