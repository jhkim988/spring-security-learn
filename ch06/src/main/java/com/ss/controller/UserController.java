package com.ss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.dto.Register;
import com.ss.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping()
	public Register.Response register(@RequestBody Register.Request registerRequest) {
		boolean result = userService.register(registerRequest);
		return new Register.Response(result, result ? "성공" : "실패");
	}
}
