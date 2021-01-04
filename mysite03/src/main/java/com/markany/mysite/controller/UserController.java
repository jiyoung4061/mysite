package com.markany.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.markany.mysite.service.UserService;
import com.markany.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, @ModelAttribute UserVo userVo) { // @ModelAttribute : 자동으로 userVo를
																				// forwarding에 넘겨줌!
		UserVo authUser = userService.getUser(userVo);
		if (authUser == null) {
			return "user/login";
		}

		// 인증처리
		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		// ACL(접근제어) - 인증이 되었을때만 들어올수 있음
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}

		// 로그아웃 처리
		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		// ACL(접근제어) - 인증이 되었을때만 들어올수 있음
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		
		Long no = authUser.getNo();
		UserVo userVo = userService.getUser(no);
		
		model.addAttribute("vo", userVo);		
		return "user/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpSession session, UserVo userVo) {
		//ACL
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		
		Long no = authUser.getNo();
		userVo.setNo(no);
		
		authUser.setName(userVo.getName());
		
		userService.updateUser(userVo);		
		return "redirect:/user/update";
	}

}
