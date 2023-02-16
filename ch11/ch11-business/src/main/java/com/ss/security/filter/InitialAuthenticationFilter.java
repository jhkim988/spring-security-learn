package com.ss.security.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ss.security.authentication.OtpAuthentication;
import com.ss.security.authentication.UsernamePasswordAuthentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

	private AuthenticationManager manager;

	@Autowired
	public void setManager(AuthenticationManager manager) {
		this.manager = manager;
	}
	
	
	@Value("${jwt.signing.key}")
	private String signingKey;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String username = request.getHeader("username");
		String password = request.getHeader("password");
		String code = request.getHeader("code");
		if (code == null) {
			authenticateByUsernamePassword(username, password);
		} else {
			authenticateByOtp(username, code);
			String jwt = createJwt(username);
			response.setHeader("Authorization", jwt);
		}
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return !request.getServletPath().equals("/login");
	}

	private Authentication authenticateByUsernamePassword(String username, String password) {
		Authentication a = new UsernamePasswordAuthentication(username, password);
		return manager.authenticate(a);
	}
	
	private Authentication authenticateByOtp(String username, String code) {
		Authentication a = new OtpAuthentication(username, code);
		return manager.authenticate(a);
	}
	
	private String createJwt(String username) {
		SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
		return Jwts.builder().setClaims(Map.of("username", username)).signWith(key).compact();
	}
}
