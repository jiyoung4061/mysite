package com.markany.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.markany.mysite.repository.BoardRepository;
import com.markany.mysite.vo.UserVo;
import com.markany.web.mvc.Action;
import com.markany.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo vo = (UserVo) session.getAttribute("authUser");
		
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		
		new BoardRepository().delete(no, password, vo.getNo());
		WebUtil.redirect(request, response, request.getContextPath()+"/board?a=list");
	}
}
