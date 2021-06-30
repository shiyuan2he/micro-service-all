package org.hsy.cloud.consumer.rpc.fallback;

import feign.hystrix.FallbackFactory;
import org.hsy.cloud.consumer.rpc.ProducerApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author heshiyuan
 * @date 2021/6/27 17:04
 */
@Component
public class ProducerApiFallBackFactory implements FallbackFactory<ProducerApi> {
    Logger logger = LoggerFactory.getLogger(ProducerApiFallBackFactory.class);
    @Override
    public ProducerApi create(Throwable throwable) {
        return new ProducerApi() {
            @Override
            public Map<String, String> loadBalance() {
                logger.info("降级了呢222222");
                Map<String, String> ret = new HashMap<>(4);
                ret.put("msg", "降级了呢22222");
                return ret;
            }

            @Override
            public Map<String, String> loadBalance2() {
                logger.info("降级了3333");
                Map<String, String> ret = new HashMap<>(4);
                ret.put("msg", "降级了3333");
                return ret;
            }
        };
    }
}
