package com.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;

/**
 * 功能名:应用出初始化
 * 
 */
@WebListener("应用出初始化")
public class InitServletListener extends HttpServlet implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		arg0.getServletContext().setInitParameter("webAppRootKey", Constants.KEYS);
		new InitServlet(arg0.getServletContext());
	}

}
