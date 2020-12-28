package com.markany.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.markany.mysite.repository.GuestBookRepository;
import com.markany.mysite.vo.GuestBookVo;
import com.markany.web.mvc.Action;
import com.markany.web.util.WebUtil;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String message = request.getParameter("message");
		String reg_date = request.getParameter("reg_date");

		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		vo.setReg_date(reg_date);

		new GuestBookRepository().insert(vo);
		WebUtil.redirect(request, response, request.getContextPath()+"/guestbook?a=list");
	}

}
