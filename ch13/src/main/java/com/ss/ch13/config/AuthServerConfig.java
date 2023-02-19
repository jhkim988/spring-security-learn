package com.ss.ch13.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}


	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		/* 방법 1. 암호 그랜트 유형 */
		/*
		var service = new InMemoryClientDetailsService();
		var cd = new BaseClientDetails();
		cd.setClientId("client");
		cd.setClientSecret("secret");
		cd.setScope(List.of("read"));
		cd.setAuthorizedGrantTypes(List.of("password"));
		service.setClientDetailsStore(Map.of("client", cd));
		clients.withClientDetails(service);
		*/

		/* 방법 2. 짧게 쓰는 방법 */
		/*
		clients.inMemory()
				.withClient("client")
				.secret("secret")
				.authorizedGrantTypes("password")
				.scopes("read");
		*/
		/* 방법 3. 승인 코드 그랜트 유형 */
		// 승인 코드 그랜트 유형을 지원하기 위해서는, 로그인을 위해 사용자를 Redirection 할 URL 도 제공해야 한다.
		/*
		clients.inMemory()
				.withClient("client")
				.secret("secret")
				.authorizedGrantTypes("authorization_code")
				.scopes("read")
				.redirectUris("http://localhost:9090/home"); // 인증을 마친 사용자에게 Redirection 할 URL
		*/
		/* 방법 4. 각기 다른 허가를 이용하는 여러 클라이언트가 있을 때 */
		/*
		clients.inMemory()
				.withClient("client1")
				.secret("secret1")
				.authorizedGrantTypes("authorization_code")
				.scopes("read")
				.redirectUris("http://localhost:9090/home")
					.and()
				.withClient("client2")
				.secret("secret2")
				.authorizedGrantTypes("authorization_code", "password", "refresh_token")
				.scopes("read")
				.redirectUris("http://localhost:9090/home");
		// 주의할 점: 클라이언트 자격 증명을 공유해서는 안된다!
		*/

		/* 방법 5. 클라이언트 자격 증명 */
		/*
		clients.inMemory()
				.withClient("client")
				.secret("secret")
				.authorizedGrantTypes("client_credentials")
				.scopes("info");
		*/

		/* 방법 6. 갱신 토큰 그랜트 */
		clients.inMemory()
				.withClient("client")
				.secret("secret")
				.authorizedGrantTypes("password", "refresh_token")
				.scopes("read");
	}
}
