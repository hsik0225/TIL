package com.hyunsiks.spring5.main;

import com.hyunsiks.spring5.config.DbConfig;
import com.hyunsiks.spring5.dao.MemberDao;
import com.hyunsiks.spring5.domain.Member;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainForMemberDao {
    private static MemberDao memberDao;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class);

        memberDao = ctx.getBean(MemberDao.class);

         selectAll();
         updateMember();
         insertMember();

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

        memberDao.update(member);
        System.out.println("암호 변경 : " + oldPw + " > " + newPw);
    }

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");

    public static void insertMember() {
        System.out.println("--------insertMember");

        String prefix = formatter.format(LocalDateTime.now());
        Member member = new Member(prefix + "@test.com", prefix, prefix, LocalDateTime.now());
        memberDao.insert(member);
        System.out.println(member.getId() + "데이터 추가");
    }
}
