package ru.mts.springaop.aop;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.mts.springaop.exceptions.DuplicateRecordException;
import ru.mts.springaop.exceptions.ResourceNotFoundException;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class ErrorAspect {

    private static final String DEFAULT_ERROR_MESSAGE = "неизвестная ошибка";
    private static final String DEFAULT_METHOD_NAME = "не удалось определить";
    private static final String DEFAULT_MASK = "В методе {} произошло: {}";


    @AfterThrowing(pointcut = "target(ru.mts.springaop.service.BookServiceImpl)", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        Logger logger = LogManager.getLogger(fetchJoinPointObject(joinPoint));

        String errorMessage = fetchMessage(exception);
        String info = fetchMethodName(joinPoint);

        logger.error(DEFAULT_MASK, info, errorMessage, exception);
    }

    private String fetchMessage(Exception exception) {
        if (exception instanceof DuplicateRecordException ex) {
            return ex.getMessage();
        } else if (exception instanceof ResourceNotFoundException notFoundException) {
            return notFoundException.getMessage();
        }
        return DEFAULT_ERROR_MESSAGE;
    }

    private Object fetchJoinPointObject(JoinPoint joinPoint) {
        return Optional.of(joinPoint)
                .map(JoinPoint::getTarget)
                .orElseGet(joinPoint::getThis);
    }

    private String fetchMethodName(JoinPoint joinPoint) {
        Optional<MethodSignature> methodSignature = Optional.of(joinPoint)
                .map(JoinPoint::getSignature)
                .map(MethodSignature.class::cast);

        String methodName =  methodSignature
                .map(MethodSignature::getMethod)
                .map(Method::getName)
                .filter(StringUtils::isNotBlank)
                .orElse(DEFAULT_METHOD_NAME);

        String methodArguments = methodSignature
                .map(CodeSignature::getParameterNames)
                .map(Arrays::asList)
                .map(StringUtils::join)
                .orElse(StringUtils.EMPTY);

        return methodName + "(" + methodArguments + ")";
    }
}
