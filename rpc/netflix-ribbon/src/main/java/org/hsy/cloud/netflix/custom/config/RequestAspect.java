package org.hsy.cloud.netflix.custom.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author heshiyuan
 * @date 2021/6/30 15:02
 */
@Component
@Aspect
public class RequestAspect {

    @Pointcut("execution(* org.hsy.cloud.netflix.ribbon.controller.*.*(..))")
    private void anyControllerMethod(){}

    @Before(value = "anyControllerMethod()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String version = request.getHeader("version");
        RibbonParameters.set(version);
    }
}
