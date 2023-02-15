package com.ss.sec03;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

//@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf(c-> {
			c.ignoringAntMatchers("/ciao");
		});
/*
		MvcRequestMatcher 를 이용하는 방법
		HandlerMappingIntrospector i = new HandlerMappingIntrospector();
		MvcRequestMatcher r = new MvcRequestMatcher(i, "/ciao");
		c.ignoringRequestMatchers(r);
*/
/*
		 RegexRequestMatcher 를 이용하는 방법
		 String pattern = ".*[0-9].*";
		 String httpMethod = HttpMethod.POST.name();
		 RegexRequestMatcher r = new RegexRequestMatcher(pattern, httpMethod);
		 c.ignoringRequestMathcers(r);
 */
		http.authorizeRequests().anyRequest().permitAll();
	}
}
