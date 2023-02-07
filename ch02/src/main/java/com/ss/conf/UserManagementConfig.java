package com.ss.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserManagementConfig {

	// 방법 1
	@Bean
	UserDetailsService userDetailService() {
		// var: 구문을 간소하게 만들고, 세부 정보를 감춘다.
		var userDetailsService = new InMemoryUserDetailsManager();
		
		// 사용자 생성, 하나 이상의 권한을 지정해야한다.
		// authorities: 해당 사용자에게 허용된 작업
		var user = User.withUsername("john").password("12345").authorities("read").build();
		userDetailsService.createUser(user);
		return userDetailsService;
	}

	// UserDetailsService 를 재정의하면, PasswordEncoder 도 다시 선언해야 한다.
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	// 방법 2
/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		var userDetailsService = new InMemoryUserDetailsManager();
		var user = User.withUsername("john").password("12345").authorities("read").build();
		userDetailsService.createUser(user);
		
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	// 방법 1과 방법 2를 혼합하면 헷갈릴 수 있어 권장하지 않는다.
*/
}
