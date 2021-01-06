package com.markany.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.markany.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			// DefaultServletHandler가 처리하는 경우(보통 assets의 정적 자원 접근)
			return true;
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. handler Method에 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. Method에 @Auth가 안붙어 있는 경우,Type(Class)에 붙어 있는지 확인한다
//		if(auth == null) {
//			// 과제
//		}
		
		// 5. Method나 Type(Class)에 @Auth가 없는 경우
		if(auth == null) {
			return true;
		}
		
		// 6. @Auth가 붙어있기 때문에 인증(Authentification) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		// 7. Authorization 체크 과제
		
		
		
		// 7. 권한(Authorization) 체크를 위해서 @Auth의 role 가져오기("USER", "ADMIN")
//		 String role = auth.value();
		
		// 8. @Auth의 role이 "USER"인 경우에는 authUser의 role이 "USER", "ADMIN" 상관없음
//		if("USER".equals(role)) {
//			return true;
//		}
		return false;
	}

}
