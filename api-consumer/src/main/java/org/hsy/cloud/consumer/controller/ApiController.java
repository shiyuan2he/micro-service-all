package org.hsy.cloud.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author heshiyuan
 * @date 2021/6/25 14:43
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Value("${spring.application.name}")
    private String name;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/lb")
    public Map<String, String> loadBalance(){
        Map<String, String> ret = new HashMap<>(4);
        ret.put("hostname", name);
        ret.put("port", port);
        return ret;
    }
}
