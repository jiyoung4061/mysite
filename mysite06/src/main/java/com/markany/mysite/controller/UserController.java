package com.markany.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.markany.mysite.service.UserService;
import com.markany.mysite.vo.UserVo;
import com.markany.security.Auth;
import com.markany.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) { // join.jsp를 같이 사용하니까 uservo가 없으면 오류남!
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) { // valid한 결과를 result에 
					// @ModelAttribute 어노테이션 : 넘겨주지않아도 UserVo-class이름의 첫글자만 소문자로 바뀌어서 modelattribute에 들어감
		if(result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error : list ) {
//				System.out.println(error);
//			}
			model.addAllAttributes(result.getModel());
			return "user/join"; // error발생시 다시 join으로
		}
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {

		Long no = authUser.getNo();
		UserVo userVo = userService.getUser(no);
		System.out.println("userVo:"+userVo);
		model.addAttribute("vo", userVo);
		return "user/update";
	}

//	@Auth(value="USER", role=Role.USER)
	@Auth // @Auth("USER") user일때 접근가능한 부분
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo userVo) {
		Long no = authUser.getNo();
		userVo.setNo(no);
		
		authUser.setName(userVo.getName());
		
		userService.updateUser(userVo);
		return "redirect:/user/update";
	}
	
	@RequestMapping(value="/auth", method = RequestMethod.POST)
	public void auth() {
		
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public void logout() {
		
	}

//	@ExceptionHandler(Exception.class)
//	public String handleException() {
//		return "error/exception";
//	}
}
