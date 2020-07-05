package com.hyunsiks.spring5;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class ExeTimeAspect {

    @Pointcut("execution(public * com.hyunsiks.spring5..*Calculator.*(..))")
    private void publicTarget() {

    }

    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();

        try {
            System.out.println("Method start");
            return joinPoint.proceed();
        } finally {
            System.out.println("Method end");
            long finish = System.nanoTime();
            Signature signature = joinPoint.getSignature();

            System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    signature.getName(),
                    Arrays.toString(joinPoint.getArgs()),
                    (finish - start));
        }
    }
}
