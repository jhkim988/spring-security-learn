package com.ss.sec02;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {

	private Logger logger = Logger.getLogger(ProductController.class.getName());
	
	// 변경을 GET 으로 보내면 CSRF 토큰 검사를 하지 않기 때문에 주의해야한다.
	@PostMapping("/add")
	public String add(@RequestParam String name) {
		logger.info("Adding product " + name);
		return "main.html";
	}
}
