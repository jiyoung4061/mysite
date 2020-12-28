package com.markany.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.markany.mysite.repository.GuestBookRepository;
import com.markany.web.mvc.Action;
import com.markany.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		
		new GuestBookRepository().delete(no, password);
		WebUtil.redirect(request, response, request.getContextPath()+"/guestbook?a=list");
	}

}
