package com.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "AllFilter", description = "编码转换过滤器", urlPatterns = "/*")
public class AllFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		szbm(req, res);// 编码
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		chain.doFilter(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	protected void szbm(ServletRequest request, ServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
