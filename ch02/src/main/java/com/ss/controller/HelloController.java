package com.ss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	// 서버가 시작되면 <password> 가 생성된다.
	// Base64 로 <user>:<password> 를 인코딩하여 요청 헤더에
	// Authorization: Basic [인코딩 결과]
	// 를 추가하여 요청을 보낸다.
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello, Security!";
	}
}
