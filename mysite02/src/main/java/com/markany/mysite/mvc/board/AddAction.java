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
		Long maxGroupNo = new BoardRepository().findMaxGroupNo();
		
		if (authUser != null) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String no = request.getParameter("no"); // 부모 게시글 no
			
			BoardVo vo = new BoardVo();
			vo.setContents(content);
			vo.setTitle(title);
			vo.setUserNo(authUser.getNo());
			vo.setUserName(authUser.getName());
			vo.setHit(0L);
			
			if(no == null || "".equals(no)) { // 새 글쓰기
				vo.setOrderNo(1);
				vo.setDepth(1);
				vo.setGroupNo(maxGroupNo+1);
			} else { // 댓글 쓰기
				BoardVo parentVo = new BoardRepository().findByNo(Long.valueOf(no));
				vo.setDepth(parentVo.getDepth()+1);
				vo.setGroupNo(parentVo.getGroupNo());
				vo.setOrderNo(parentVo.getOrderNo()+1);
				new BoardRepository().setForOrderNo(vo);
			}
			
			new BoardRepository().insert(vo);
		}
		WebUtil.redirect(request, response, request.getContextPath()+"/board?a=list");
	}
}
