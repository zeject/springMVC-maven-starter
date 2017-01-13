package com.util.jdbc;

import java.util.ArrayList;
import java.util.List;

public class JdbcExecute {

	private List<String> sqlList = new ArrayList<String>();

	private List<Object[]> csList = new ArrayList<Object[]>();

	public void addSql(String sql, Object[] objs) {
		this.sqlList.add(sql);
		this.csList.add(objs);
	}

	public boolean execute() {
		return Jdbc.execute(sqlList, csList);
	}

}
