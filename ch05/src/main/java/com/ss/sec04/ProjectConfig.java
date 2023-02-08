package com.ss.sec04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private CustomAuthenticationFailureHandler authenticationFailureHandler;
	
	// 인증 방식을 양식 기반 로그인으로 변경하고 싶다면
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.formLogin() // FormLoginConfigurer<HttpSecurity> 반환
//			.defaultSuccessUrl("/home", true); // 기본 성공 URL 설정
//		http.authorizeRequests().anyRequest().authenticated();
	
		// formLogin 방식과 httpBasic 방식 모두 적용하고 싶다면:
		http.formLogin()
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler)
		.and()
			.httpBasic()
		.and()
			.authorizeRequests()
			.anyRequest().authenticated();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
