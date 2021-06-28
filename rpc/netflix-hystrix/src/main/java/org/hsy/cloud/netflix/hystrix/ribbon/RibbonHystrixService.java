package org.hsy.cloud.netflix.hystrix.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author heshiyuan
 * @date 2021/6/27 18:33
 */

@Service
public class RibbonHystrixService {
    @Resource
    RestTemplate restTemplate;

    AtomicLong start = new AtomicLong(0L);
    AtomicLong end = new AtomicLong(0L);
    /**
     * 配合@EnableHystrix使用
     * @return
     */
    @HystrixCommand(defaultFallback = "loadBalanceBack")
    public Map<String, String> loadBalance(){
        start.set(System.currentTimeMillis());
        HashMap<String, String> ret = restTemplate.getForObject("http://ProducerApplication/api/lb", HashMap.class);
        end.set(System.currentTimeMillis());
        System.out.println("耗时： " + (end.get() - start.get()));
        return ret;
    }

    public Map<String, String> loadBalanceBack(){
        Map<String, String> ret = new HashMap<>();
        ret.put("msg", "降级fdsfs了呢");
        end.set(System.currentTimeMillis());
        System.out.println("耗时222： " + (end.get() - start.get()));
        return ret;
    }

}
