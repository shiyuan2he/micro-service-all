package org.hsy.cloud.consumer.rpc;
import org.hsy.cloud.consumer.rpc.fallback.ProducerApiFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author heshiyuan
 * @date 2021/6/26 19:06
 */
@FeignClient(value = "producer", fallbackFactory = ProducerApiFallBackFactory.class)
public interface ProducerApi {

    @GetMapping("/api/lb")
    Map<String, String> loadBalance();

    @GetMapping("/api/lb2")
    Map<String, String> loadBalance2();
}
