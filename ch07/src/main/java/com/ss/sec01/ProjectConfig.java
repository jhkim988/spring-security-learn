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

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService userDetailsService() {
		var manager = new InMemoryUserDetailsManager();
		var user1 = User.withUsername("john").password("12345").authorities("READ").build();
		var user2 = User.withUsername("jane").password("12345").authorities("WRITE").build();
		manager.createUser(user1);
		manager.createUser(user2);
		return manager;
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		
		// 모든 요청에 대해 액세스 허용
		http.authorizeRequests().anyRequest().permitAll();
	
		// WRITE 권한이 있는 사용자만 접근 허용
		http.authorizeRequests().anyRequest().hasAuthority("WRITE");
	
		// WRITE 나 READ 중 하나라도 권한이 있는 사용자에게 접근 허용
		http.authorizeRequests().anyRequest().hasAnyAuthority("WRITE", "READ");
	
		// SpEL 이용
		http.authorizeRequests().anyRequest().access("hasAuthority('WRITE')");
		http.authorizeRequests().anyRequest().access("hasAuthority('read') and !hasAuthority('delete')");
	}
}
