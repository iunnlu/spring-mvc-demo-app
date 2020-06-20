package com.ilhan.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {
    //setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    //setup pointcut declarations
    @Pointcut("execution(* com.ilhan.springdemo.controller.*.*(..))")
    private void forControllerPackage() {}

    //do the same for service and dao
    @Pointcut("execution(* com.ilhan.springdemo.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.ilhan.springdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}

    //add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        //display the method we are calling
        String theMethod = joinPoint.getSignature().toShortString();
        myLogger.info("========>> in @Before: calling method: " + theMethod);

        //display the arguments to the method

        //get the arguments
        Object[] args = joinPoint.getArgs();

        //loop thru and display args
        for(Object tempArgs:args) {
            myLogger.info("=====> arguments: " + tempArgs);
        }
    }

    //add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint joinPoint, Object theResult) {
        //display method we are returning from
        String theMethod = joinPoint.getSignature().toShortString();
        myLogger.info("===========> in @AfterReturning: from method: " + theMethod);

        //display data returned
        myLogger.info("========>> result: " + theResult);
    }


}
