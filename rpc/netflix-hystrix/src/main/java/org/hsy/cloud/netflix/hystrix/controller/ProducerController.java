package org.hsy.cloud.netflix.hystrix.controller;

import org.hsy.cloud.netflix.hystrix.ribbon.RibbonHystrixService;
import org.hsy.cloud.netflix.hystrix.rpc.ProducerApi;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/hystrix")
public class ProducerController {
    @Resource
    private ProducerApi producerApi;
    @Resource
    private RibbonHystrixService ribbonHystrixService;

    @GetMapping("/feign")
    public Map<String, String> feign(){
        return producerApi.loadBalance();
    }

    @GetMapping("/feign2")
    public Map<String, String> feign2(){
        return ribbonHystrixService.loadBalance();
    }

}
