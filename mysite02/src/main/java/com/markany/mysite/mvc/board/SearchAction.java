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

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("kwd");
		String strOfPage = request.getParameter("p");
		Long page;
		if(strOfPage == null || "".equals(strOfPage)) {
			page = 1L;
		} else {
			page = Long.valueOf(strOfPage);
		}
		List<BoardVo> list = new BoardRepository().findByKeyword(keyword, page);
		int maxPage  = (new BoardRepository().countOfBoardWithKeyword()/10) + 1;
		
		request.setAttribute("list", list);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("p", page);
		request.setAttribute("pagename", "search");
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}
}
