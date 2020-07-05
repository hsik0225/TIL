package com.hyunsiks.spring5.main;

import com.hyunsiks.spring5.Calculator;
import com.hyunsiks.spring5.config.AppAspectCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspect {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppAspectCtx.class);

        Calculator calculator = ctx.getBean("calculator", Calculator.class);
        calculator.factorial(7);
        calculator.factorial(7);
        calculator.factorial(5);
        calculator.factorial(5);
        ctx.close();
    }
}
