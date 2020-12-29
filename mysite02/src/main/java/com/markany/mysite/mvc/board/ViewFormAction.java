package com.markany.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.markany.mysite.repository.BoardRepository;
import com.markany.mysite.vo.BoardVo;
import com.markany.web.mvc.Action;
import com.markany.web.util.WebUtil;

public class ViewFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.valueOf(request.getParameter("no"));
		
		BoardVo vo = new BoardRepository().findByNo(no);
		request.setAttribute("vo", vo);
		WebUtil.forward(request, response, "WEB-INF/views/board/view.jsp");
	}
}
