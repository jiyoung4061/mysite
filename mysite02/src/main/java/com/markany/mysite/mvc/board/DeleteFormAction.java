package com.markany.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.markany.web.mvc.Action;
import com.markany.web.util.WebUtil;

public class DeleteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("no", request.getParameter("no"));
		WebUtil.forward(request, response, "WEB-INF/views/board/deleteform.jsp");
	}
}
