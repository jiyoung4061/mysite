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

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password= request.getParameter("password");
		String gender = request.getParameter("gender");
		HttpSession session = request.getSession();
		Long no = ((UserVo) session.getAttribute("authUser")).getNo();
		
		UserVo updateVo = new UserVo();
		updateVo.setGender(gender);
		updateVo.setName(name);
		updateVo.setPassword(password);
		updateVo.setNo(no);
		
		new UserRepository().update(updateVo);		
		session.setAttribute("authUser", updateVo);
		WebUtil.forward(request, response, "/WEB-INF/views/user/updatesuccess.jsp");
	}

}
