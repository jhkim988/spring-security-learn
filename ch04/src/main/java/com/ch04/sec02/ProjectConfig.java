package com.ch04.sec02;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
public class ProjectConfig {
	
	// DelegatingPasswordEncoder 는 여러 암호화 알고리즘을 사용하고 싶을 때 사용한다.
	// {noop}12345 를 인코딩하면, 중괄호 내부의 값을 파싱하여, 해당 값을 키로써 map 에서 알고리즘을 찾는다.
	@Bean
	PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("scrypt", new SCryptPasswordEncoder());
		
		// 표준 제공 PasswordEncoder 의 구현에 대한 맵을 갖고 있는 delegatingPasswordEncoder
		// default 는 bcrypt 이다.
//		PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return new DelegatingPasswordEncoder("bcrypt", encoders); // default 는 bcrypt
	}
}
