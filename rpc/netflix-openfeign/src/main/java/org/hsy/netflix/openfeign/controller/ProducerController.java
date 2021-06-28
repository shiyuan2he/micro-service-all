package org.hsy.netflix.openfeign.controller;

import org.hsy.netflix.openfeign.rpc.ConsumerApi;
import org.hsy.netflix.openfeign.rpc.ProducerApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author heshiyuan
 * @date 2021/6/26 19:10
 */
@RestController
@RequestMapping("/api")
public class ProducerController {
    @Resource
    private ProducerApi producerApi;
    @Resource
    private ConsumerApi consumerApi;

    @GetMapping("/feign")
    public Map<String, String> feign(){
        return producerApi.loadBalance();
    }

    @GetMapping("/feign2")
    public Map<String, String> feign2(){
        return consumerApi.loadBalance();
    }
}
