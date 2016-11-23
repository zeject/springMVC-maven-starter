package com.config;

import javax.servlet.ServletContext;

public class Constants {
	public static ServletContext servletContext;

	public static final String typeJSON = "application/json";
	public static final String userMap = "usermap"; // session
	public static final String userAdminMap = "useradminmap"; // session
	public static String Domain = ".abc.com";
	public static String url = "http://www.abc.com";

	public static String tzw1 = "л";
	public static String tzw2 = "ъ";
	public static String tzw3 = "ш";
	public static String tzw4 = "н";
	public static String tzw5 = "в";
	public static String tzw6 = "о";
	public static String tzw7 = "п";
	public static String DATE = "yyyy-MM-dd HH:mm:ss";

	public final static String AESKEY = "aptx4869";

	public static final String KEYS = "projectKey";
	public static final String hostName = "";
	public static final String senderEmail = "";
	public static final String senderName = "";
	public static final String sender = "";
	public static final String pwd = "";

	public static final String cookie = "cookie";
	public static final String adminCookie = "adminCookie";

	/*
	 * 数据库相关变量
	 */
	public static final String Jdbc_Url = "";
	public static final String Jdbc_Username = "";
	public static final String Jdbc_Password = "";

	/**
	 * redis
	 */
	public static String REDIS_IP = ""; // ip
	public static int REDIS_PORT = 6379; // 端口
	public static String REDIS_PASS = "";// 密码

	/**
	 * upyun
	 */
	public static String upyunName = "";
	public static String upyunUserName = "";
	public static String upyunPassword = "";

	/**
	 * 极光推送
	 */
	public static String JPushMasterSecret = "";
	public static String JPushAppKey = "";
	// key:secret base64加密
	public static String JPushAuthor = "";

}
