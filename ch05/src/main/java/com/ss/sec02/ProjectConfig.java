package com.ss.sec02;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContextHolder;

//@Configuration
//@EnableAsync
public class ProjectConfig {
	// SerurityContextHolder 는 스레드 안전을 지원하지 않는다.
	
	// 엔드포인트가 비동기가 되면(@Async), 메서드를 실행하는 스레드와 요청을 수행하는 스레드가 다른 스레드가 된다.
	// 따라서 기본 보안 컨텍스트(ThreadLocal) 으로 보안 컨텍스트가 유지되지 않는다.
	// 그러나 @Async 를 이용하지 않고, 직접 스레드를 만들면, 이 방법으로도 해결할 수 없다.
	// -> 프레임워크가 모르는 방법으로 스레드가 시작되면 빈틈이 생긴다.
	// 보안 컨텍스트의 모드를 바꾸는 방법
	@Bean
	InitializingBean initializingBean() {
		// GLOBAL 모드: 모든 스레드가 같은 보안 컨텍스트에 접근한다.
		// GLOBAL 모드는 웹 서버에는 이용되지 않지만, 독립형 애플리케이션에서는 사용할 수 있다.
//		return () -> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
		return () -> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	}
}
