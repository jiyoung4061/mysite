package com.markany.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.markany.mysite.mvc.main.MainActionFactory;
import com.markany.web.mvc.Action;
import com.markany.web.mvc.ActionFactory;

public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		String configPath = getServletConfig().getInitParameter("config"); 
		System.out.println("init() called:" + configPath);
		super.init();
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("service() called");
		super.service(arg0, arg1);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() called");

		String actionName = request.getParameter("a");		

		
		ActionFactory actionFactory = new MainActionFactory();
		Action action = actionFactory.getAction(actionName);
		action.execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("destroy() called!!!!!!!!!");
		super.destroy();
	}
}


/*
 * url mapping을 mysite02/main이 아니라
 * mysite02/ 로만 하고싶어서 url pattern을 /로만 하고싶어서 바꾸게되면
 * CSS나 사진이 안먹음 왜? css의 경로도 maincontroller가 다 받아버려서!!
 */