package org.hsy.cloud.api.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author heshiyuan
 * @date 2021/6/25 14:43
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    Logger logger = LoggerFactory.getLogger(ApiController.class);
    @Value("${spring.application.name}")
    private String name;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/lb")
    public Map<String, String> loadBalance() throws InterruptedException {
        long start = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(5);
        Map<String, String> ret = new HashMap<>(4);
        ret.put("hostname", name);
        ret.put("port", port);
        long end = System.currentTimeMillis();
        logger.info("sss=" + (end - start));
        return ret;
    }

    @RequestMapping("/lb2")
    public Map<String, String> loadBalance2(){
        Map<String, String> ret = new HashMap<>(4);
        ret.put("hostname", name);
        ret.put("port", port);
        return ret;
    }
}
