package com.util.jdbc;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.config.Constants;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBOperator {

	private static Log logger = LogFactory.getLog(DBOperator.class);

	public static ComboPooledDataSource dsWebgame = null;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			logger.error("", e1);
			e1.printStackTrace();
		}
		dsWebgame = new ComboPooledDataSource();
		try {
			dsWebgame.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		// 参数由 Config 类根据配置文件读取
		// 设置JDBC的URL sp501a38f1195ed.mysql.rds.aliyuncs.com
		dsWebgame.setJdbcUrl(Constants.Jdbc_Url);
		// 设置数据库的登录用户名
		dsWebgame.setUser(Constants.Jdbc_Username);
		// dsWebgame.setUser("root");
		// 设置数据库的登录用户密码
		dsWebgame.setPassword(Constants.Jdbc_Password);
		// dsWebgame.setPassword("zxc123123");
		// 设置连接池的最大连接数
		dsWebgame.setMaxPoolSize(12);
		// 设置连接池的最小连接数
		dsWebgame.setMinPoolSize(10);
		// 设置连接池的初始化连接数
		dsWebgame.setInitialPoolSize(5);

		dsWebgame.setTestConnectionOnCheckin(true);// 数据库断开后是否自动重连
		dsWebgame.setIdleConnectionTestPeriod(60);// 重连时间60秒

		dsWebgame.setMaxStatements(0);// 有时候会产生死锁，将maxStatements设置为0
		try {
			dsWebgame.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("", e);
			e.printStackTrace();
		}
	}
}
