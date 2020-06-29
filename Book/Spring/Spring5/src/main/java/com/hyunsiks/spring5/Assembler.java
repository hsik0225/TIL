package com.hyunsiks.spring5;

import com.hyunsiks.spring5.dao.MemberDao;
import com.hyunsiks.spring5.service.ChangePasswordService;
import com.hyunsiks.spring5.service.MemberRegisterService;

public class Assembler {

    private MemberDao memberDao;
    private MemberRegisterService registerService;
    private ChangePasswordService changePasswordService;

    public Assembler() {
        memberDao = new MemberDao();
        registerService = new MemberRegisterService(memberDao);
        changePasswordService = new ChangePasswordService();
        changePasswordService.setMemberDao(memberDao);
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public MemberRegisterService getRegisterService() {
        return registerService;
    }

    public ChangePasswordService getChangePasswordService() {
        return changePasswordService;
    }
}
