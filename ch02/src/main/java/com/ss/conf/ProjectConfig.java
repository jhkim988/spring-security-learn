package com.ss.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	
/*
	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider);
	}
*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic(); // Http Basic 접근 인증 구성, 사용자 이름과 암호를 Authorization 헤더를 통해 보내면 된다.
		http.authorizeRequests()
			.anyRequest().authenticated(); // 모든 요청에 인증을 요구하도록 지정한다.
	}
	
	// 권장하지 않는 방식
/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("john")
			.password("12345")
			.authorities("read")
		.and()
			.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
*/
}
