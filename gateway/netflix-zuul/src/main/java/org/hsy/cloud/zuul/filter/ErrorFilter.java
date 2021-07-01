package org.hsy.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @author heshiyuan
 * @date 2021/6/30 16:56
 */
@Component
public class ErrorFilter extends ZuulFilter {
    /**
     * 其他阶段发生异常，错误是调用
     * 1. 异常统一处理
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    /**
     * 数字越小越先执行
     * @return
     */
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
