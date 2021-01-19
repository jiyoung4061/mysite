package com.markany.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping({"","/main"}) // url 여러개 설정O
	public String index() {
		return "main/index";
	}
}
