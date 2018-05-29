package com.github.chanming2015.spring.boot.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.github.chanming2015.utils.log.LoggerAop;
import com.github.chanming2015.utils.log.SimpleLogFormater;

/**
 * Project:domain
 * Package:com.github.chanming2015.spring.boot.test.aop
 * FileName:DefaultAopLogger.java
 * Comments:
 * JDK Version:
 * @author XuMaoSen
 * Create Date:2015年12月4日 下午7:41:50
 * Description:
 * Version:1.0.0
 */
public class DefaultAopLogger implements LoggerAop
{

    /** @author XuMaoSen
     */
    @Override
    public void beforeAdvice(String param)
    {
    }

    /** @author XuMaoSen
     */
    @Override
    public void afterReturningAdvice(Object retVal)
    {
    }

    /** @author XuMaoSen
     */
    @Override
    public void afterThrowingAdvice(Exception exception)
    {
    }

    /** @author XuMaoSen
     */
    @Override
    public void afterFinallyAdvice()
    {
    }

    /** @author XuMaoSen
     */
    @Override
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable
    {
        // 获取被代理的对象名
        String targetName = joinPoint.getSignature().getDeclaringTypeName();
        // 获取被调用的方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取方法参数
        Object[] arguments = joinPoint.getArgs();
        Logger logger = LoggerFactory.getLogger(targetName);
        logger.info(SimpleLogFormater.formatParams(methodName + "()", arguments));

        Object proceed = null;
        try
        {
            // 调用被代理对象方法
            proceed = joinPoint.proceed();
        }
        catch (Exception e)
        {
            String responseInfo = SimpleLogFormater.formatException(methodName + "()", e);
            logger.error(responseInfo);
            JSONObject result = new JSONObject();
            result.put("errMsg", e.getMessage());
            return result;
        }

        if (proceed instanceof JSONObject)
        {
            JSONObject result = (JSONObject) proceed;
            String responseInfo = SimpleLogFormater.formatResult(result);
            logger.info(responseInfo);
        }
        else
        {
            String responseInfo = proceed != null ? proceed.toString() : "Result is null";
            logger.info(responseInfo);
        }
        return proceed;
    }

}
