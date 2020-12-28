package com.markany.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.markany.mysite.repository.BoardRepository;
import com.markany.mysite.vo.BoardVo;
import com.markany.mysite.vo.UserVo;
import com.markany.web.mvc.Action;
import com.markany.web.util.WebUtil;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser != null) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			BoardVo vo = new BoardVo();
			vo.setContents(content);
			vo.setTitle(title);
			vo.setUserNo(authUser.getNo());
			vo.setUserName(authUser.getName());
			vo.setDepth(1);
			vo.setGroupNo(1L);
			vo.setHit(0L);
			vo.setOrderNo(1);

			new BoardRepository().insert(vo);
		}
		WebUtil.redirect(request, response, request.getContextPath()+"/board?a=list");
	}
}
