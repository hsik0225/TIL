package com.hyunsiks.spring5.main;

import com.hyunsiks.spring5.config.DbConfig;
import com.hyunsiks.spring5.exception.MemberNotFoundException;
import com.hyunsiks.spring5.exception.WrongPasswordException;
import com.hyunsiks.spring5.service.ChangePasswordService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForCPS {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class);

        ChangePasswordService cps = ctx.getBean("changePasswordService", ChangePasswordService.class);

        try {
            cps.changePassword("hsik0225@gmail.com", "1235", "1111");
            System.out.println("암호를 변경했습니다");
        } catch (MemberNotFoundException e) {
            System.out.println("회원 데이터가 존재하지 않습니다");
        } catch (WrongPasswordException e) {
            System.out.println("암호가 올바르지 않습니다");
        }

        ctx.close();
    }
}
