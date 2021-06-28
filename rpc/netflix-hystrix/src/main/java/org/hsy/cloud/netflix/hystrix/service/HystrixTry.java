package org.hsy.cloud.netflix.hystrix.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixKey;

/**
 * @author heshiyuan
 * @date 2021/6/27 15:10
 */
public class HystrixTry extends HystrixCommand {

    protected HystrixTry(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected Object run() throws Exception {
        int i = 2 / 0 ;
        return "ok";
    }

    @Override
    protected Object getFallback() {
        return "降级了";
    }

    public static void main(String[] args) {
        HystrixTry hystrixTry = new HystrixTry(HystrixCommandGroupKey.Factory.asKey(""));
        System.out.println(hystrixTry.execute());
    }
}
