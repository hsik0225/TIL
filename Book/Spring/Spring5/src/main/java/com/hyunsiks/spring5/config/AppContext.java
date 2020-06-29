package com.hyunsiks.spring5.config;

import com.hyunsiks.spring5.MemberInfoPrinter;
import com.hyunsiks.spring5.MemberListPrinter;
import com.hyunsiks.spring5.MemberSummaryPrinter;
import com.hyunsiks.spring5.VersionPrinter;
import com.hyunsiks.spring5.service.ChangePasswordService;
import com.hyunsiks.spring5.service.MemberRegisterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

    @Bean
    public MemberListPrinter memberListPrinter() {
        return new MemberListPrinter();
    }

    @Bean
    public MemberInfoPrinter MemberInfoPrinter() {
        return new MemberInfoPrinter();
    }

    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(2);
        return versionPrinter;
    }


    @Bean
    public MemberRegisterService memberRegisterService() {
        return new MemberRegisterService();
    }

    @Bean
    public ChangePasswordService changePasswordService() {
        return new ChangePasswordService();
    }

    @Bean
    public MemberSummaryPrinter memberSummaryPrinter() {
        return new MemberSummaryPrinter();
    }
}
