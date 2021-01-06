package com.markany.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice // advice가 나오면? AOP이구나!
public class GlobalExceptionHandler {

	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) throws Exception {
		// 1. 로깅(Logging)
		StringWriter errors = new StringWriter(); // 버퍼
		e.printStackTrace(new PrintWriter(errors));
		LOGGER.error(errors.toString());
		
		// 2. 사과(안내 페이지로 forwarding, 정상 종료)
		ModelAndView mav = new ModelAndView();
		mav.addObject("errors", errors);
		mav.setViewName("error/exception");
		
		return mav; 
	}

}
