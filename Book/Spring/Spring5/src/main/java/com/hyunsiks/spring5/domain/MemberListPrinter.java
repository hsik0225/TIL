package com.hyunsiks.spring5.domain;

import com.hyunsiks.spring5.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;


public class MemberListPrinter {

    private MemberDao memberDao;

    private MemberPrinter printer;

    public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
        this.memberDao = memberDao;
        this.printer = printer;
    }

    public MemberListPrinter() {
    }

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Autowired
    public void setPrinter(MemberSummaryPrinter printer) {
        this.printer = printer;
    }

    public void printAll() {
        Collection<Member> members = memberDao.selectAll();
        members.forEach(member -> printer.print(member));
    }
}
