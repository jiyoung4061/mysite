package com.markany.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.markany.mysite.repository.BoardRepository;
import com.markany.mysite.vo.BoardVo;
import com.markany.web.mvc.Action;
import com.markany.web.util.WebUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strOfPage = request.getParameter("p");
		Long page;
		if(strOfPage == null || "".equals(strOfPage)) {
			page = 1L;
		} else {
			page = Long.valueOf(strOfPage);
		}
		List<BoardVo> list = new BoardRepository().findAll(page-1);
		int maxPage  = (new BoardRepository().countOfBoard()/10) + 1;
		
		request.setAttribute("list", list);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("p", page);
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}
}
