package com.markany.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.markany.mysite.repository.BoardRepository;
import com.markany.mysite.vo.BoardVo;
import com.markany.web.mvc.Action;
import com.markany.web.util.WebUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		Long no = Long.valueOf(request.getParameter("no"));
		
		BoardVo vo = new BoardVo();
		vo.setContents(contents);
		vo.setTitle(title);
		vo.setNo(no);
		
		new BoardRepository().updateByVo(vo, "modify");
		WebUtil.redirect(request, response, request.getContextPath()+"/board?a=viewform&no="+vo.getNo());
		
	}
}
