package com.jackyzeng.logging.aspect;


import com.jackyzeng.logging.annotation.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
@Aspect
public class SysLogAspect {

    @Pointcut("@annotation(com.jackyzeng.logging.annotation.SysLog)")
    public void loggingPointCut() {

    }

    @Around("loggingPointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        String className = pjp.getTarget().getClass().getName();
        String methodName = signature.getName();

        SysLog sysLog = method.getAnnotation(SysLog.class);
        String operator = sysLog.value();
        long beginTime = System.currentTimeMillis();
        Object returnValue = null;
        Exception exception = null;

        try {
            returnValue = pjp.proceed();
            return returnValue;
        } catch (Exception e) {
            exception = e;
            throw e;
        } finally {
            long costTime = System.currentTimeMillis() - beginTime;
            if (Objects.nonNull(exception)) {
                log.error("[class: {}][method: {}][operator: {}][args: {}][exception occur: {}]",
                        className, methodName, operator, pjp.getArgs(), exception);
            } else {
                log.info("[class: {}][method: {}][operator: {}][costTime: {}ms][args: {}][return: {}]",
                        className, methodName, operator, costTime, pjp.getArgs(), returnValue);
            }
        }
    }
}
