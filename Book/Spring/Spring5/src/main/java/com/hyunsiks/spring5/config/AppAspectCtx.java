package com.hyunsiks.spring5.config;

import com.hyunsiks.spring5.aspect.CacheAspect;
import com.hyunsiks.spring5.aspect.Calculator;
import com.hyunsiks.spring5.aspect.ExeTimeAspect;
import com.hyunsiks.spring5.aspect.RecCalculator;
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
