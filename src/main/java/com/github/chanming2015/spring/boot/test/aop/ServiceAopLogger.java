package com.github.chanming2015.spring.boot.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAopLogger extends DefaultAopLogger{

    @Pointcut(value = "execution(public com.alibaba.fastjson.JSONObject com.github.chanming2015.spring.boot.test.service.impl.*.*(..))")
    public void doServicePoint() {	}

    @Override
    @Around(value="doServicePoint()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        return super.aroundAdvice(pjp);
    }

}
