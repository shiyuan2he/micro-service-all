package org.hsy.cloud.netflix.custom.loadbalance;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.hsy.cloud.netflix.custom.config.RibbonParameters;

import java.util.List;
import java.util.Map;

/**
 * @author heshiyuan
 * @date 2021/6/30 14:28
 */
public class VersionRule extends AbstractLoadBalancerRule {

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    public Server choose(ILoadBalancer lb, Object key){
        List<Server> serverList = lb.getReachableServers();
        if(null==serverList || serverList.size()==0){
            return null;
        }
        Server server = null;
        for (int i = 0; i < serverList.size(); i++) {
            server = serverList.get(i);

            Map<String, String> metadata = ((DiscoveryEnabledServer) server).getInstanceInfo().getMetadata();
            if(null!=metadata){
                String version = RibbonParameters.get();
                if(null!=version&&version.equals(metadata.get("version"))){
                    break;
                }
            }
        }
        return server;
    }
}
