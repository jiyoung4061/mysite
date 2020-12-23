package com.markany.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.markany.mysite.repository.UserRepository;
import com.markany.mysite.vo.UserVo;
import com.markany.web.mvc.Action;
import com.markany.web.util.WebUtil;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password= request.getParameter("password");
		
		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPassword(password);
		UserVo userVo = new UserRepository().findByEmailAndPassword(vo);
		if(userVo == null) {
			request.setAttribute("email", email);
			WebUtil.forward(request, response, "WEB-INF/views/user/loginform.jsp");
			return;
		}
		
		/* 로그인 처리 */
		
		// session manager에 session 만들기 요청
		HttpSession session = request.getSession(true); // true : 없으면 만들어주세요 (default : false)
		session.setAttribute("authUser", userVo);
		WebUtil.redirect(request, response, request.getContextPath());
	}

}
