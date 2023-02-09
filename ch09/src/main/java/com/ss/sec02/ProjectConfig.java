package com.ss.sec02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private StaticKeyAuthenticationFilter filter;
	
	@Autowired
	private AuthenticationLoggingFilter loggingFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 기존 필터의 위치에 다른 필터를 적용하면, 필터가 대체되는 것이 아니다.
		// 순서가 보장되지 않고, 둘 다 실행된다.
		http
			.addFilterAt(filter, BasicAuthenticationFilter.class)
			.addFilterAfter(loggingFilter , filter.getClass())
		.authorizeRequests()
			.anyRequest().permitAll();
	}
}
