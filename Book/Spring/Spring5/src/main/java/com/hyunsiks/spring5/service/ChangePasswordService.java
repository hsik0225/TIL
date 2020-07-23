package com.hyunsiks.spring5.service;

import com.hyunsiks.spring5.domain.Member;
import com.hyunsiks.spring5.dao.MemberDao;
import com.hyunsiks.spring5.exception.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ChangePasswordService {

    private MemberDao memberDao;

    @Transactional
    public void changePassword(String email, String oldPassword, String newPassword) {
        Member member = memberDao.selectByEmail(email);
        if (member == null)
            throw new MemberNotFoundException();
        member.changePassword(oldPassword, newPassword);
        memberDao.update(member);
    }

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
