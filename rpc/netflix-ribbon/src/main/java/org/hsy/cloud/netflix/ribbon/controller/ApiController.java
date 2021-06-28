package org.hsy.cloud.netflix.ribbon.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author heshiyuan
 * @date 2021/6/25 14:40
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Resource
    LoadBalancerClient loadBalancerClient;

    @Resource
    RestTemplate restTemplate;

    @RequestMapping("/lb")
    public Map<String, String> loadBalance(){
        ServiceInstance instance = loadBalancerClient.choose("ProducerApplication");
        System.out.println(instance.toString());
        return restTemplate.getForObject("http://"+instance.getHost()+":" + instance.getPort() + "/api/lb", HashMap.class);
    }

    /**
     * @RestTemplate添加注解  //    @LoadBalanced
     * @return
     */
    @RequestMapping("/lb2")
    public Map<String, String> loadBalance2(){
        ServiceInstance instance = loadBalancerClient.choose("ProducerApplication");
        System.out.println(instance.toString());
        return restTemplate.getForObject("http://ProducerApplication/api/lb", HashMap.class);
    }
}
