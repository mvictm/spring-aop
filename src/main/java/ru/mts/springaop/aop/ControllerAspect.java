package ru.mts.springaop.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Log4j2
@Component
public class ControllerAspect {

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
        //nothing
    }

    @Before("target(ru.mts.springaop.controller.BookController) && publicMethod()")
    public void logBefore() {
        log.info("Лог до начала исполнения метода контроллера");
    }

    @After("within(ru.mts.springaop.controller.*) && publicMethod()")
    public void logAfter() {
        log.info("Лог после исполнения метода контроллера");
    }
}
