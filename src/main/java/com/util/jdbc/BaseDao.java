package com.util.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class BaseDao {

	private static JdbcTemplate jdbcTemplate;

	public static JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate != null) {
			return jdbcTemplate;
		}
		jdbcTemplate = new JdbcTemplate(new DataSource() {

			public PrintWriter getLogWriter() throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}

			public int getLoginTimeout() throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}

			public Logger getParentLogger() {
				// TODO Auto-generated method stub
				return null;
			}

			public void setLogWriter(PrintWriter arg0) throws SQLException {
				// TODO Auto-generated method stub

			}

			public void setLoginTimeout(int arg0) throws SQLException {
				// TODO Auto-generated method stub

			}

			public boolean isWrapperFor(Class<?> arg0) throws SQLException {
				// TODO Auto-generated method stub
				return false;
			}

			public <T> T unwrap(Class<T> arg0) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}

			public Connection getConnection() throws SQLException {
				return DBOperator.dsWebgame.getConnection();
				// TODO Auto-generated method stub
			}

			public Connection getConnection(String username, String password) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}

		});
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		BaseDao.jdbcTemplate = jdbcTemplate;
	}

}