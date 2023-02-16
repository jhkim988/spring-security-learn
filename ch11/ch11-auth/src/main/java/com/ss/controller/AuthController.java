package com.ss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.entity.Otp;
import com.ss.entity.User;
import com.ss.service.UserService;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/user/add")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@PostMapping("/user/auth")
	public void auth(@RequestBody User user) {
		userService.auth(user);
	}
	
	/*
	 * 단순한 check 이기 때문에 Get Method 를 이용하였음.
	 * QueryString 을 통해 필요한 인자들을 받을 생각이었지만, @RequestParam 이 Otp 객체로 만들어주지 않는 문제 발생
	 * @RequestParam 을 없애면 Otp 객체로 만들어 준다는 사실을 알아냄
	 * */
	
	@GetMapping("/otp/check")
	public ResponseEntity<String> check(Otp otp) {
		if (userService.check(otp)) {
			return ResponseEntity.ok("success");
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("failure");
		}
	}
}
