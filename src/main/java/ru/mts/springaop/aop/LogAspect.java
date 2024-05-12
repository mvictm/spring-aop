package ru.mts.springaop.aop;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.mts.springaop.annotations.Logging;

import java.util.Optional;

@Aspect
@Log4j2
@Component
public class LogAspect {
    private static final String ENTER = ">> {}";
    private static final String EXIT = "<< {}";

    @Around("@annotation(logging) && execution(* ru.mts..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint, Logging logging) throws Throwable {
        String annotationValue = fetchAnnotationValue(joinPoint, logging);
        Logger logger = LogManager.getLogger(fetchJoinPointObject(joinPoint));

        logger.info(ENTER, annotationValue);

        Object result = joinPoint.proceed();

        logger.info(EXIT, annotationValue);

        return result;
    }

    private String fetchAnnotationValue(ProceedingJoinPoint joinPoint, Logging logging) {
        return Optional.ofNullable(logging)
                .map(Logging::name)
                .filter(StringUtils::isNotBlank)
                .orElse(joinPoint.getSignature().getName());
    }

    private Object fetchJoinPointObject(ProceedingJoinPoint joinPoint) {
        Object targetObject = joinPoint.getTarget();
        return targetObject != null ? targetObject : joinPoint.getThis();
    }
}
