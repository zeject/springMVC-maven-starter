package com.config;

import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = { "/" }, name = "servlet1", description = "springMVC", initParams = {
		@WebInitParam(name = "contextConfigLocation", value = "classpath*:/**/dispatcherServlet-*.xml"),
		@WebInitParam(name = "ERROR", value = "error.view") }, loadOnStartup = 1)
@MultipartConfig
public class SpringMVC extends DispatcherServlet {

	public SpringMVC() {
		System.out.println(2222);
	}

}
