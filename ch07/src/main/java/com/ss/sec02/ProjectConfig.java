package com.ss.sec02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		var manager = new InMemoryUserDetailsManager();
		
		// 역할은 ROLE_ 접두사를 갖는다.
		var user1 = User.withUsername("john").password("12345").authorities("ROLE_ADMIN").build();
		var user2 = User.withUsername("jane").password("12345").authorities("ROLE_MANAGER").build();
		
		// roles 메서드를 이요하면 자동으로 ROLE_ 접두사를 추가해준다.
		user1 = User.withUsername("john").password("12345").roles("ADMIN").build();
		user2 = User.withUsername("jane").password("12345").roles("MANAGER").build();
		
		manager.createUser(user1);
		manager.createUser(user2);
		
		return manager;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		
		// ROLE_ 접두사를 갖지 않는 것을 주의
		http.authorizeRequests().anyRequest().hasRole("ADMIN");
		
		// 모든 엔드포인트에 접근 제한
		http.authorizeRequests().anyRequest().denyAll();
	}
}
