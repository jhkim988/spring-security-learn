package com.ss.sec02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication a = context.getAuthentication();
		return "Hello, " + a.getName() + "!";
	}
	
	@GetMapping("/hello2")
	public String hello2(Authentication a) {
		return "Hello2, " + a.getName() + "!";
	}
	
	
	// DelegatingSecurityContextCallable: 데코레이터 패턴으로 구현됨
	@GetMapping("/ciao")
	public String ciao() throws Exception {
		Callable<String> task = () -> {
			SecurityContext context = SecurityContextHolder.getContext();
			return context.getAuthentication().getName();
		};
		
		ExecutorService e = Executors.newCachedThreadPool();
		try {
//			return "Ciao, " + e.submit(task.get()) + "!";
			return "Ciao, " + e.submit(new DelegatingSecurityContextCallable<>(task)).get() + "!";
		} finally {
			e.shutdown();
		}
	}
	
	// DelegatingSecurityContextExecutorService
	@GetMapping("/hola")
	public String hola() throws Exception {
		Callable<String> task = () -> {
			SecurityContext context = SecurityContextHolder.getContext();
			return context.getAuthentication().getName();
		};
		ExecutorService e = Executors.newCachedThreadPool();
		e = new DelegatingSecurityContextExecutorService(e);
		try {
			return "Hola, " + e.submit(task).get() + "!";
		} finally {
			e.shutdown();
		}
	}
}
