package com.ss.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@PropertySource("classpath:config.properties")
@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	@Value("${github.client-id}")
	private String clientId;

	@Value("${github.client-secret}")
	private String clientSecret;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.oauth2Login(c -> {
			c.clientRegistrationRepository(clientRepository());
		});
		http.authorizeRequests()
				.anyRequest()
				.authenticated();
	}

	private ClientRegistration clientRegistration() {
		// 방법 1
/*
		ClientRegistration.withRegistrationId("github")
				.clientId(clientId)
				.clientSecret(clientSecret)
				.scope(new String[] { "read:user" })
				.authorizationUri("https://github.com/login/oauth/authorize")
				.tokenUri("https://github.com/login/oauth/access_token")
				.userInfoUri("https://api.github.com/user")
				.userNameAttributeName("id")
				.clientName("Github")
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUriTemplate("{baseUrl}/{action}/oauth2/code/{registrationId}")
				.build();
*/
		// 방법2
		return CommonOAuth2Provider.GITHUB.getBuilder("github")
				.clientId(clientId)
				.clientSecret(clientSecret)
				.build();
	}

	public ClientRegistrationRepository clientRepository() {
		var c = clientRegistration();
		return new InMemoryClientRegistrationRepository(c);
	}
	

}
