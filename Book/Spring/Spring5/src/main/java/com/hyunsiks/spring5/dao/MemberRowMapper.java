package com.hyunsiks.spring5.dao;

import com.hyunsiks.spring5.domain.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {

    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member member = new Member(
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getTimestamp("register_date_time").toLocalDateTime()
        );

        member.setId(rs.getLong("ID"));
        return member;
    }
}
