package org.hsy.cloud.netflix.custom.loadbalance;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 开发一种自定义负载均衡策略，这个策略放在网关，
 * 然后请求转发时，优先将请求分发到和客户端ip相同的服务里面，
 * 这样大家可以共用一个开发平台，然后自己本地开发环境启动服务时，
 * 可以方便开发，不至于请求错乱
 * @author heshiyuan
 * @date 2021/6/25 11:40
 */
public class IpSameRule extends AbstractLoadBalancerRule {
    private static Logger log = LoggerFactory.getLogger(IpSameRule.class);
    private AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);
    private static String LOCAL_SERVER = "127.0.0.1";
    private static String REMOTE_SERVER = "127.0.0.1";

    public IpSameRule() {
        super();
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    private Server choose(ILoadBalancer lb, Object key){
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

            Integer nextServerIndex = fixedIp(allServers, serverCount);
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

    private Integer fixedIp(List<Server> allServers, int modulo) {
        String userIp = getRemoteAddr();
        Integer position = null;
        Integer localhostPosition = null;
        for (int i = 0; i < allServers.size(); i++) {
            Server server = allServers.get(i);
            if (null != server && userIp.equals(server.getHost())) {
                position = i;
                break;
            } else if (null != server && LOCAL_SERVER.equals(server.getHost())) {
                localhostPosition = i;
            }
        }
        if (null == position && null != localhostPosition) {
            position = localhostPosition;
        }
        if (null == position) {
            return incrementAndGetModulo(modulo);
        }
        return position;
    }

    private String getRemoteAddr() {
        /*RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String remoteAddr = "0.0.0.0";
        if (request.getHeader("X-FORWARDED-FOR") != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
        } else {
            remoteAddr = request.getRemoteAddr();
        }
        return remoteAddr;*/
        return REMOTE_SERVER;
    }

}
