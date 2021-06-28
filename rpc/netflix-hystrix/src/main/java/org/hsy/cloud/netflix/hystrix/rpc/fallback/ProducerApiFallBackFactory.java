package org.hsy.cloud.netflix.hystrix.rpc.fallback;

import feign.hystrix.FallbackFactory;
import org.hsy.cloud.netflix.hystrix.rpc.ProducerApi;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author heshiyuan
 * @date 2021/6/27 17:04
 */
@Component
public class ProducerApiFallBackFactory implements FallbackFactory<ProducerApi> {

    @Override
    public ProducerApi create(Throwable throwable) {
        return () -> {
            System.out.println("降级了呢222222");
            Map<String, String> ret = new HashMap<>(4);
            ret.put("msg", "降级了呢22222");
            return ret;
        };
    }
}
