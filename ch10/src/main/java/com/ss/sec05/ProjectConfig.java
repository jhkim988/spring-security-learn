package com.ss.sec05;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors(c -> {
			CorsConfigurationSource source = request -> {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(List.of("example.com", "example.org"));
				config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // 기본적으로 아무 메서드도 지정하지 않기 때문에, 이를 설정해야한다.
				return config;
			};
			c.configurationSource(source);
		});
		http.authorizeRequests().anyRequest().permitAll();
	}
}
