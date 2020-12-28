package com.markany.mysite.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ContextLoadListener
 * 
 * Listener란?
 * tomcat안에 mysite02/WEB-INF/web.xml을 바탕으로 어플리케이션을 만들면
 * 만드는 동시에 application context loaded라는 이벤트가 발생함!
 * 그 이벤트를 받아주는(처리하는) 것이 Listener의 역할!
 *
 */
public class ContextLoadListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext context = servletContextEvent.getServletContext();
		String contextConfigLocation = context.getInitParameter("contextConfigLocation");

		System.out.println("Application Starts...." + contextConfigLocation);
	}
	
    public void contextDestroyed(ServletContextEvent servletContextEvent)  { 
    	
    }
}
