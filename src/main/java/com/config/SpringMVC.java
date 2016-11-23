package com.config;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import org.springframework.web.servlet.DispatcherServlet;

@WebServlet(urlPatterns = { "/" }, name = "servlet1", description = "springMVC", initParams = {
		@WebInitParam(name = "contextConfigLocation", value = "classpath*:/dispatcherServlet-*.xml"),
		@WebInitParam(name = "ERROR", value = "error.view") }, loadOnStartup = 1)
@MultipartConfig
public class SpringMVC extends DispatcherServlet {
}
