package com.hyunsiks.spring5.main;

import com.hyunsiks.spring5.config.DbConfig;
import com.hyunsiks.spring5.dao.MemberDao;
import com.hyunsiks.spring5.domain.Member;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

public class MainForMemberDao {
    private static MemberDao memberDao;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class);

        memberDao = ctx.getBean(MemberDao.class);

        selectAll();
        insertMember();
        updateMember();

         ctx.close();
    }

    public static void selectAll() {
        System.out.println("--------selectAll");

        int total = memberDao.count();

        System.out.println("전체 데이터 : " + total);

        List<Member> memberList = memberDao.selectAll();
        for (Member m : memberList) {
            System.out.println(m.getId() + " : " + m.getEmail() + " : " + m.getName());
        }
    }

    public static void updateMember() {
        System.out.println("--------updateMember");
        Member member = memberDao.selectByEmail("hsik0225@gmail.com");
        String oldPw = member.getPassword();
        String newPw = Double.toHexString(Math.random());
        member.changePassword(oldPw, newPw);

        System.out.println("oldPw = " + oldPw);
        System.out.println("newPw = " + newPw);

        memberDao.update(member);
        System.out.println("암호 변경 : " + oldPw + " > " + newPw);
    }

    public static void insertMember() {
        System.out.println("--------insertMember");

        String prefix = "hsik0225";
        Member member = new Member(prefix + "@gmail.com", prefix, prefix, LocalDateTime.now());
        memberDao.insert(member);
        System.out.println(member.getId() + " 데이터 추가");
    }
}
