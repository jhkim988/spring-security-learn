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
	
	@GetMapping("/otp/check")
	public ResponseEntity<String> check(@RequestParam Otp otp) {
		if (userService.check(otp)) {
			return ResponseEntity.ok("success");
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("failure");
		}
	}
}
