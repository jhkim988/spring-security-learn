package com.ss.sec02;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// 스프링은 필터 체인에 추가한 필터를 요청당 한 번만 실행하도록 보장하지 않는다.
// OncePerRequestFilter 는 이를 보장한다.
@Component
public class AuthenticationLoggingFilter extends OncePerRequestFilter {

	private static final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());
	
	// HTTP 필터만 지원한다.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestId = request.getHeader("Request-Id");
		logger.info("Successfully authenticated request with id " + requestId);
		filterChain.doFilter(request,  response);
	}

}
