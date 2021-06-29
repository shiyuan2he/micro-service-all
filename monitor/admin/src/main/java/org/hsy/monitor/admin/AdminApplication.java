package org.hsy.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import org.hsy.monitor.admin.dingtalk.DingDingNotifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author heshiyuan
 * @date 2021/6/28 16:36
 */
@SpringBootApplication
@EnableAdminServer
public class AdminApplication {

    public static void main(String[] args) {
        new SpringApplication(AdminApplication.class).run(args);
    }

    @Bean
    public DingDingNotifier dingDingNotifier(InstanceRepository repository) {
        return new DingDingNotifier(repository);
    }
}
