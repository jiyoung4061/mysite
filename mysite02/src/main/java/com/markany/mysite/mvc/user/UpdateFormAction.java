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

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. session에서 authUser 가져오기
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		// 접근제어필요
		if (authUser == null) {
			WebUtil.redirect(request, response, request.getContextPath());
		} else {
			// 2. authUser에서 no가져오기
			Long no = authUser.getNo();
			// 3. no를 가지고 Repository를 통해 UserVo 가져오기
			UserVo vo = new UserRepository().findByNo(no);
			// 4. jsp로 UserVo 전달하면서 forwarding하기
			request.setAttribute("vo", vo);
			WebUtil.forward(request, response, "/WEB-INF/views/user/updateform.jsp");
		}

	}

}
