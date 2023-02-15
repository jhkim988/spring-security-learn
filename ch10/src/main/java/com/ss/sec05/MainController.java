package com.ss.sec05;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	private Logger logger = Logger.getLogger(MainController.class.getName());
	
	@GetMapping("/")
	public String main() {
		return "cors.html";
	}
	
	// @CrossOrigin 을 이용하여 규직을 투명하게 지정할 수 있지만, 
	// 코드의 반복이 생길 수 있고, 엔드포인트에 애너테이션 추가를 잊을 수도 있다.
	// config 에서 설정하는 방법도 있다.
	@PostMapping("/test")
	@ResponseBody
	@CrossOrigin("http://localhost:8080")
	public String test() {
		logger.info("Test method called");
		return "HELLO";
	}
}
