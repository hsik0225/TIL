package com.hyunsiks.spring5.main;

import com.hyunsiks.spring5.config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        ClientB client = ctx.getBean(ClientB.class);
        client.send();
        ctx.close();
    }
}
