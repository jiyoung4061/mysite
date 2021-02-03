package com.markany.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.markany.mysite.dto.JsonResult;
import com.markany.mysite.service.UserService;

@Controller("userApiController")
@RequestMapping("/api/user")		
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/existemail")
	public JsonResult existEmail(@RequestParam(value = "email", required=false, defaultValue="") String email) {
		
		boolean result = userService.existsEmail(email);
		
//		Map<String, Object> map = new HashMap<>();
//		map.put("result", "success");	// "success" or "fail"
//		map.put("data", result); 		// if success, Data set (true, false)
//		map.put("message", null);		// if fail, Error Message
		
//		JsonResult jsonResult = new JsonResult();
//		jsonResult.setResult("ok");
		
		return JsonResult.success(result);
	}
}
