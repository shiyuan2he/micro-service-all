package org.hsy.cloud.consumer.loadbalance;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.apache.commons.lang.StringUtils;
import org.hsy.cloud.consumer.config.RibbonParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author heshiyuan
 * @date 2021/6/30 14:28
 */
public class VersionRule extends RandomRule {

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public Server choose(ILoadBalancer lb, Object key){
        List<Server> serverList = lb.getReachableServers();
        if(null==serverList || serverList.size()==0){
            return null;
        }
        String version = RibbonParameters.LOCAL.get();
        if(StringUtils.isBlank(version)){
            return super.choose(getLoadBalancer(), key);
        }
        List<Server> versionServerList = new ArrayList<>();
        for (int i = 0; i < serverList.size(); i++) {
            Server server = serverList.get(i);
            Map<String, String> metadata = ((DiscoveryEnabledServer) server).getInstanceInfo().getMetadata();
            if(null!=metadata&&version.equals(metadata.get("version"))){
                versionServerList.add(server);
            }
        }
        if(versionServerList.size()!=0){
            return versionServerList.get(super.chooseRandomInt(versionServerList.size()));
        }
        return null;
    }
}
