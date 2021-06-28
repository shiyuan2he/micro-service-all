package org.hsy.cloud.netflix.custom.loadbalance;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. 根据ip权重轮询
 * 2. 根据ip权重随机
 * @author heshiyuan
 * @date 2021/6/25 11:41
 */
public class IPWeightRule extends AbstractLoadBalancerRule {
    private static Logger log = LoggerFactory.getLogger(IpSameRule.class);
    private AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);

    public IPWeightRule() {
        super();
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        System.out.println("22222=" + iClientConfig.toString());
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    private Server choose(ILoadBalancer lb, Object key){
        System.out.println("11111=" + lb.toString() + "   " + key.toString());
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }

        Server server = null;
        int count = 0;
        while (count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }

            Integer nextServerIndex = fixedIndex(allServers, serverCount);
            server = allServers.get(nextServerIndex);

            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: " + lb);
        }
        return server;
    }

    private int incrementAndGetModulo(int modulo) {
        for (; ; ) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    private Integer fixedIndex(List<Server> allServers, int modulo) {
        /*Integer position = null;
        Integer localhostPosition = null;
        for (int i = 0; i < allServers.size(); i++) {
            Server server = allServers.get(i);
            if (null != server && "userIp".equals(server.getHost())) {
                position = i;
                break;
            }
        }
        if (null == position) {
            return incrementAndGetModulo(modulo);
        }
        return position;*/
        return 0;
    }
}
