package com.ss.sec03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomEntryPoint implements AuthenticationEntryPoint {
	// 인증이 실패했을 때의 응답을 구성하기 위해서는 AuthenticationEntryPoint 를 구현하고
	// configure 에서 httpBasic 의 Customizer 에서 등록한다.
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.addHeader("message", "Luke, I am your father!");
		response.sendError(HttpStatus.UNAUTHORIZED.value());
	}
	
}
