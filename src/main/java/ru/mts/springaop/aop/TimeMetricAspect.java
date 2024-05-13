package ru.mts.springaop.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class TimeMetricAspect {

    @Around("@annotation(ru.mts.springaop.annotations.TimeMetric)")
    public Object executionStopWatch(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        log.info("{} Исполнено за {} ms", joinPoint.getSignature(), end - start);

        return result;
    }
}
