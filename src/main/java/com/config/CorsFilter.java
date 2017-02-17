package com.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跨域过滤器
 * 
 * @author zeject
 *
 */
@WebFilter(urlPatterns = "/*", initParams = {
		@WebInitParam(name = "allowOrigin", value = "http://localhost,mp12345.com,http://192.168.1.202:8080"),
		@WebInitParam(name = "allowMethods", value = "*"), @WebInitParam(name = "allowCredentials", value = "true"),
		@WebInitParam(name = "allowHeaders", value = "Content-Type"),
		@WebInitParam(name = "exposeHeaders", value = "") })
public class CorsFilter implements Filter {

	private String allowOrigin;
	private String allowMethods;
	private String allowCredentials;
	private String allowHeaders;
	private String exposeHeaders;

	public void init(FilterConfig filterConfig) throws ServletException {
		allowOrigin = filterConfig.getInitParameter("allowOrigin");
		allowMethods = filterConfig.getInitParameter("allowMethods");
		allowCredentials = filterConfig.getInitParameter("allowCredentials");
		allowHeaders = filterConfig.getInitParameter("allowHeaders");
		exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// Enumeration headerNames = request.getHeaderNames();
		// while (headerNames.hasMoreElements()) {
		// String key = (String) headerNames.nextElement();
		// String value = request.getHeader(key);
		// System.out.println(key + "----" + value);
		// }

		if (!allowOrigin.equals("")) {
			List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
			if (!allowOriginList.isEmpty()) {
				String currentOrigin = request.getHeader("origin");
				// System.out.println(request.getHeader("origin"));
				if (allowOriginList.contains(currentOrigin)) {
					response.setHeader("Access-Control-Allow-Origin", currentOrigin);
				}
			}
		}
		if (!allowMethods.equals("")) {
			response.setHeader("Access-Control-Allow-Methods", allowMethods);
		}
		if (!allowCredentials.equals("")) {
			response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
		}
		if (!allowHeaders.equals("")) {
			response.setHeader("Access-Control-Allow-Headers", allowHeaders);
		}
		if (!exposeHeaders.equals("")) {
			response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
		}
		chain.doFilter(req, res);
	}

	public void destroy() {
	}
}