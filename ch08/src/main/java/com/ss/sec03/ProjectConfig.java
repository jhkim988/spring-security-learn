package com.ss.sec03;

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
		// 예제를 위해 POST, PUT, DELETE 엔드포인트를 포함해, 모든 엔드 포인트를 호출할 수 있도록 csrf 보호를 비활성화 한다.
		http.csrf().disable();
		
		http.httpBasic();
		
		// antMachers 는 "/hello/" 를 처리해주지 않는다.
		// mvcMatchers 는 스프링이 몇 가지 위험을 예방해준다.
		http.authorizeRequests().antMatchers("/hello").authenticated();
	}	
}
