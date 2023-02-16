package com.ss.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.ss.security.AuthenticationServerProxy;
import com.ss.security.authentication.OtpAuthentication;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private AuthenticationServerProxy proxy;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		var username = authentication.getName();
		var code = String.valueOf(authentication.getCredentials());
		if (proxy.sendOTP(username, code)) return new UsernamePasswordAuthenticationToken(username, code);
		throw new BadCredentialsException("Bad Credentials");
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return OtpAuthentication.class.isAssignableFrom(aClass);
	}

}
