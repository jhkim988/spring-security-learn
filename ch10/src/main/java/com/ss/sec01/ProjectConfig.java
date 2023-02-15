package com.ss.sec01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

//@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CsrfTokenLogger csrfTokenLogger;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAfter(csrfTokenLogger, CsrfFilter.class)
			.authorizeRequests()
				.anyRequest().permitAll();
	}
}
