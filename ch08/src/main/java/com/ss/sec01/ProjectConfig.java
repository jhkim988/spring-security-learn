package com.ss.sec01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		var manager = new InMemoryUserDetailsManager();
		var user1 = User.withUsername("john").password("12345").roles("ADMIN").build();
		var user2 = User.withUsername("jane").password("12345").roles("MANAGER").build();
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
		http.authorizeRequests()
			.mvcMatchers("/hello").hasRole("ADMIN")
			.mvcMatchers("/ciao").hasRole("MANAGER")
			.anyRequest().permitAll(); // 나머지 모든 엔드포인트에 대해 모든 요청 허용(명시적, 쓰지 않아도 적용됨)
//			.anyRequest().authenticated(); // 인증된 사용자에게만 나머지 모든 요청 허용
	}
}
