package com.markany.mysite.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // advice가 나오면? AOP이구나!
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) throws Exception {
		// 1. 로깅(Logging)
		e.printStackTrace();
		// 2. 사과(안내 페이지로 forwarding, 정상 종료)
		return "error/exception"; 
	}

}
