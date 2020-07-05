package com.hyunsiks.spring5.service;

import com.hyunsiks.spring5.exception.DuplicateMemberException;
import com.hyunsiks.spring5.domain.Member;
import com.hyunsiks.spring5.dao.MemberDao;
import com.hyunsiks.spring5.domain.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MemberRegisterService {

    @Autowired
    private MemberDao memberDao;

    public MemberRegisterService() {

    }

    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req) {
        Member member = memberDao.selectByEmail(req.getEmail());
        if (member != null) {
            throw new DuplicateMemberException("dup eamil " + req.getEmail());
        }

        Member newMember = new Member(
                req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now()
        );

        System.out.println(newMember);

        memberDao.insert(newMember);
        return newMember.getId();
    }
}
