package com.ss.sec03;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	// realm: 특정 인증 방식을 이용하는 보호 공간
	// Customizer<HttpBasicConfigurerMHttpSecurity>> 형식의 객체
	// HttpBasicConfigureer<HttpSecurity> 의 realmName 을 호출하여 영역 이름을 변경할 수 있다.
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.httpBasic(c -> {
			c.realmName("OTHER");
			c.authenticationEntryPoint(new CustomEntryPoint());
		});
	}
}
