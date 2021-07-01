package org.hsy.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

/**
 * @author heshiyuan
 * @date 2021/6/30 16:55
 */
@Component
public class PostFilter extends ZuulFilter {
    /**
     * 请求调用微服务之后调用
     * 1. 添加header
     * 2. 记录日志
     * 3. 将响应发送给客户端
     * @return
     */
    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
