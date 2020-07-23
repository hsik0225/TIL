package com.hyunsiks.spring5.dao;

import com.hyunsiks.spring5.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class MemberDao {

    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query(
                "SELECT * FROM member m WHERE email = ?",
                new MemberRowMapper(),
                email);

        return results.isEmpty() ? null : results.get(0);
    }

    public void insert(final Member member) {

        // GeneratedKeyHolder 클래스는 자동 생성된 키 값을 구해주는 KeyHolder 구현 클래스이다
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // update 메소드는 PreparedStatement 객체와 KeyHolder 객체를 파라미터로 갖는다
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(
                        "INSERT INTO member (email, password, name, regdate) " +
                                "VALUES (?, ?, ?, ?)",

                        // 자동 생성되는 키 칼럼 목록을 지정한다
                        new String[] {"id"}
                );

                preparedStatement.setString(1, member.getEmail());
                preparedStatement.setString(2, member.getPassword());
                preparedStatement.setString(3, member.getName());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
                return preparedStatement;
            }
        }, keyHolder);
    }

    public void update(Member member) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                // 파라미터로 전달받은 Connection을 이용해서 PreparedStatement ㅐㅇ성
                PreparedStatement statement = con.prepareStatement(
                        "INSERT INTO member (email, password, name, regdate) VALUES (?, ?, ?, ?)"
                );

                // 인덱스 파라미터의 값 설정
                statement.setString(1, member.getEmail());
                statement.setString(2, member.getPassword());
                statement.setString(3, member.getName());
                statement.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));

                // 생성한 PreparedStatement 객체 리턴
                return statement;
            }
        });
    }

    public List<Member> selectAll() {
        return jdbcTemplate.query("SELECT * FROM member m", new MemberRowMapper());
    }

    public int count() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM member m ", Integer.class);
    }
}
