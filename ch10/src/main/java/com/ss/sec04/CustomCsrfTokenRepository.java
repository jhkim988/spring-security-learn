package com.ss.sec04;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

//@Component
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public CsrfToken generateToken(HttpServletRequest request) {
		String uuid = UUID.randomUUID().toString();
		return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
	}

	@Override
	public void saveToken(CsrfToken csrf, HttpServletRequest request, HttpServletResponse response) {
		String identifier = request.getHeader("X-IDENTIFIER");
		Optional<Token> existingToken = tokenRepository.findTokenByIdentifier(identifier);
		if (existingToken.isPresent()) {
			Token token = existingToken.get();
			token.setToken(csrf.getToken());
		} else {
			Token token = new Token();
			token.setToken(csrf.getToken());
			token.setIdentifier(identifier);
			tokenRepository.save(token);
		}
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest request) {
		String identifier = request.getHeader("X-IDENTIFIER");
		Optional<Token> existingToken = tokenRepository.findTokenByIdentifier(identifier);
		if (existingToken.isPresent()) {
			Token token = existingToken.get();
			return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.getToken());
		}
		return null;
	}
}
