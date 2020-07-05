package com.hyunsiks.spring5.config;

import com.hyunsiks.spring5.CacheAspect;
import com.hyunsiks.spring5.Calculator;
import com.hyunsiks.spring5.ExeTimeAspect;
import com.hyunsiks.spring5.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppAspectCtx {

    @Bean
    public CacheAspect cacheAspect() {
        return new CacheAspect();
    }

    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect();
    }

    @Bean
    public Calculator calculator() {
        return new RecCalculator();
    }
}
