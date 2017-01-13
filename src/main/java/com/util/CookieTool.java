package com.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.config.Constants;

public class CookieTool {
	
	private static String domain = Constants.Domain;

	/**
	 * 清除cookie
	 * @param domain
	 * @param cookieName
	 * @param request
	 * @param response
	 * @return
	 */
	public static boolean removeCookie(String cookieName, HttpServletRequest request, HttpServletResponse response) {
		return removeCookie(domain, cookieName, "/", null, 0, true, request, response);
	}
	/**
	 * 
	 * @param domain
	 * @param cookieName
	 * @param path
	 * @param value
	 * @param maxage
	 * @param httponly
	 * @param request
	 * @param response
	 * @return @return
	 */
	private static boolean removeCookie(String domain, String cookieName, String path, String value, int maxage, boolean httponly, HttpServletRequest request, HttpServletResponse response) {
		if (domain == null || cookieName == null || request == null || response == null) {
			return false;
		}
		Cookie cookie = CookieTool.getCookieByName(request, cookieName);
		if (cookie != null) {
			cookie.setPath("/");
			cookie.setValue(null);
			cookie.setDomain(domain);
			cookie.setMaxAge(0);
			cookie.setHttpOnly(true);
			response.addCookie(cookie);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 新增cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @return
	 */
	public static boolean addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		if (name == null || value == null || response == null || domain == null) {
			return false;
		}
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		cookie.setDomain(domain);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		return true;
	}

	/**
	 * 查找指定cookie
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 遍历cookie返回Map
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

}
