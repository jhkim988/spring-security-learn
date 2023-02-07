package com.ss.sec03;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class ProjectConfig {
	
	// JdbcUserDetailsManager 기본 테이블로 users, authorities 테이블을 이용한다.
	// users: username, password, enabled 컬럼
	// authorities: id, username, authority 컬럼
	// UserDetailsManager: createUser, updateUser, deleteUser, changePassword, userExists
	@Bean
	UserDetailsService userDetailsService(DataSource dataSource) {
		String usersByUsernameQuery = "select username, password, enabled from users where username = ?";
		String authsByUserQuery = "select id, username, authority from authorities where username = ?";
		var userDetailsManager = new JdbcUserDetailsManager(dataSource);
		userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
		userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
		// -> 기타 모든 쿼리를 변경할 수 있다.
		return userDetailsManager;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
