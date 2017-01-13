package com.config;

import javax.servlet.ServletContext;

/**
 * 功能名:微薄
 * 
 */
//@WebServlet(name="initServlet",loadOnStartup=100,description="系统配制加载")
public class InitServlet {
	private ServletContext context=null;
	public InitServlet(ServletContext c) {
		context=c;
		init();
	}
	


	

	public void init() {
		
	}

	
}
