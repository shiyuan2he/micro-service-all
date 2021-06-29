package org.hsy.cloud.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heshiyuan
 * @date 2021/6/29 10:48
 */
@RestController
@RefreshScope
public class ApiController {

    @Value("${xxoo:000}")
    private String xxoo;

    @GetMapping("/config/info")
    public String getConfigInfo(){
        return xxoo;
    }
}
