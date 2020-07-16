package com.hyunsiks.spring5.config;

import com.hyunsiks.spring5.dao.MemberDao;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DbConfig {

    // close 메소드는 커넥션 풀에 보관된 Connection을 닫는다
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();

        // JDBC 드라이버 클래스를 지정한다. MySQl 드라이버 클래스를 사용한다
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        // JDBC URL을 지정한다. 데이터베이스와 테이블의 캐릭터셋을 UTF-8로 설정했으므로
        // CharacterEncoding 파라미터를 이용해서 MySQL에 연결할 때 사용할 캐릭터셋을 UTF-8로 지정
        dataSource.setUrl("jdbc:mysql://localhost/ever?characterEncoding=utf8");
        dataSource.setUsername("ever");
        dataSource.setPassword("asdqwe1!");
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(10);
        return dataSource;
    }

    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource());
    }
}
