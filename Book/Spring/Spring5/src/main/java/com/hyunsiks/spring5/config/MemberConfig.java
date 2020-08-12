package com.hyunsiks.spring5.config;

import com.hyunsiks.spring5.spring.ChangePasswordService;
import com.hyunsiks.spring5.spring.MemberDao;
import com.hyunsiks.spring5.spring.MemberRegisterService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MemberConfig {

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		// JDBC 드라이버 클래스를 지정한다. MySQl 드라이버 클래스를 사용한다
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");

		// JDBC URL을 지정한다. 데이터베이스와 테이블의 캐릭터셋을 UTF-8로 설정했으므로
		// CharacterEncoding 파라미터를 이용해서 MySQL에 연결할 때 사용할 캐릭터셋을 UTF-8로 지정
		ds.setUrl("jdbc:mysql://localhost/ever?characterEncoding=utf8");
		ds.setUsername("ever");
		ds.setPassword("asdqwe1!");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
		return ds;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}

	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}

	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}

	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}
}
