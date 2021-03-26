package com.markany.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.markany.mysite.service.MainService;
import com.markany.mysite.vo.H2oFeet;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;

	@RequestMapping({"","/main"}) // url 여러개 설정O
	public String index() {
		return "main/index";
	}

	@GetMapping(path="/h2os")
	public @ResponseBody List<H2oFeet> h2o_list() {
		
		return mainService.h2o_list();
	}
}
