package org.hsy.cloud.netflix.custom.config;

import org.springframework.stereotype.Component;

/**
 * @author heshiyuan
 * @date 2021/6/30 14:38
 */
@Component
public class RibbonParameters {
    private static final ThreadLocal<String> local = new ThreadLocal(){
        @Override
        public String initialValue(){
            return "";
        }
    };

    public static String  get(){
        return local.get();
    }

    public static void set(String version){
        local.set(version);
    }
}
