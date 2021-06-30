package org.hsy.cloud.eureka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author heshiyuan
 * @date 2021/6/29 20:31
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // 为了可以使用 http://${user}:${password}@${host}:${port}/eureka/
        // 这种方式登录,所以必须是httpBasic,
        // 如果是form方式,不能使用url格式登录
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
            .and()
                .httpBasic();
    }
}
