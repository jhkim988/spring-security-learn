package com.ss.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ss.security.filter.InitialAuthenticationFilter;
import com.ss.security.filter.JwtAuthenticationFilter;
import com.ss.security.provider.OtpAuthenticationProvider;
import com.ss.security.provider.UsernamePasswordAuthenticationProvider;

/*
 * 스프링부트 2.7 버전에서 순환참조 이슈가 있음.
 * WebSecurityConfigurerAdapter 가 Deprecated 되면서 발생하는 이슈로 보임
 * 스프링부트의 버전을 2.3으로 바꿔서 임시로 해결함
 * WebSecurityConfigurerAdapter 의 대체 방법으로 변환해야할 것
 * */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private OtpAuthenticationProvider otpAuthenticationProvider;
	
	@Autowired
	private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
	
	@Autowired
	private InitialAuthenticationFilter initialAuthenticationFilter;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(otpAuthenticationProvider)
			.authenticationProvider(usernamePasswordAuthenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)
			.addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
		http.authorizeRequests().anyRequest().authenticated();
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
