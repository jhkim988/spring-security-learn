package com.ss.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ss.entity.Authority;
import com.ss.entity.User;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails {
	
	private final User user;
	private List<Authority> authorities;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}

	public CustomUserDetails(User user, List<Authority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities.stream().map(a -> new SimpleGrantedAuthority(a.getName())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
