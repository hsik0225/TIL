package com.hyunsiks.spring5.config;

import com.hyunsiks.spring5.*;
import com.hyunsiks.spring5.dao.MemberDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
@Import(AppContext.class)
@ComponentScan(
        basePackages = {"spring5"}
)
public class AppConfigImport {

    @Bean
    public Greeter greeter() {
        Greeter g = new Greeter();
        g.setFormat("%s, 안녕하세요!");
        return g;
    }

    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinterA() {
        return new MemberPrinter();
    }
}
