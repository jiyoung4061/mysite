package com.markany.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor01 implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	// handler: 
			throws Exception { // handler가 넘어가기 전에
		System.out.println("MyInterceptor:preInterceptor...");
		return true; // false로 하게되면 handler로 안가고 바로 response 보냄
	} 

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception { // handler로 넘어간 후 호출
		System.out.println("MyInterceptor:preInterceptor...");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {  // forwarding이 끝난 후 호출
		System.out.println("MyInterceptor:preInterceptor...");
	}

}
