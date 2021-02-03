package com.markany.mysite.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.markany.mysite.dto.JsonResult;

@ControllerAdvice // advice가 나오면? AOP이구나!
public class GlobalExceptionHandler {

	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public void handleException(
					HttpServletRequest request,
					HttpServletResponse response,
					Exception e) throws Exception {
		// 1. 로깅(Logging)
		StringWriter errors = new StringWriter(); // 버퍼
		e.printStackTrace(new PrintWriter(errors));
		LOGGER.error(errors.toString());
		
		// 2. 요철구분
		// 만약 JSON요청의 경우 request header의 Accept application/json
		// 만약 HTML요청의 경우 request header의 Accept text/html
		// 만약 jpg요청의 경우 request header의 Accept iamge/jpeg
		String accept = request.getHeader("accept");
		if(accept.matches(".*application/json.*")) { // application/json이 포함된 모든 문자열들
			/* JSON응답 */
			response.setStatus(HttpServletResponse.SC_OK);
			
			JsonResult jsonResult = JsonResult.fail(errors.toString());
			
			String jsonString = new ObjectMapper().writeValueAsString(jsonResult);
			
			OutputStream os = response.getOutputStream();
			os.write(jsonString.getBytes("UTF-8"));
			os.close();			
		} else {
			// 3. 사과(안내 페이지로 forwarding, 정상 종료)
			request.setAttribute("errors", errors.toString());
			request
				.getRequestDispatcher("WEB-INF/views/error/exception.jsp")
				.forward(request, response);
		}
	}

}
