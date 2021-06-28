package org.hsy.cloud.netflix.hystrix.rpc.fallback;

import org.hsy.cloud.netflix.hystrix.rpc.ProducerApi;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author heshiyuan
 * @date 2021/6/27 17:04
 */
@Component
public class ProducerApiFallBack implements ProducerApi {

    @Override
    public Map<String, String> loadBalance() {
        System.out.println("降级了呢");
        Map<String, String> ret = new HashMap<>();
        ret.put("msg", "降级了呢");
        return ret;
    }
}
