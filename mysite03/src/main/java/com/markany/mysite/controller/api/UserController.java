package com.markany.mysite.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("UserApiController")
@RequestMapping("/api/user")		
public class UserController {
	
	@ResponseBody
	@RequestMapping("/existemail")
	public Map<String, Object> existEmail(@RequestParam(value = "email", required=false, defaultValue="") String email) {
		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
		map.put("data", true); // exist: true, not exist: false
		map.put("message", null);
		
		return map;
	}
}
