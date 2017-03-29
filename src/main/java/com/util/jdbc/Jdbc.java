package com.util.jdbc;

import com.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Jdbc {

    public static JdbcTemplate getJdbcTemplate() {
        return BaseDao.getJdbcTemplate();
    }

    private static Logger logger = LogManager.getLogger(Jdbc.class);

    private static int pageSize = 15;

    public static List find(String sql) {
        return BaseDao.getJdbcTemplate().queryForList(sql);
    }

    public static List find(String sql, Object... obj) {
        return BaseDao.getJdbcTemplate().queryForList(sql, obj);
    }

    public static long findLong(String sql, Object... obj) {
        Object o = BaseDao.getJdbcTemplate().queryForObject(sql, Long.class, obj);
        if (o == null || o.toString().equals("")) {
            return 0;
        }
        return Long.valueOf(o.toString());
    }

    public static List findRow(String sql, Class clazz, Object... obj) {
        return BaseDao.getJdbcTemplate().queryForList(sql, clazz, obj);
    }

    public static Map findMap(String sql, Object... obj) {
        List list = find(sql, obj);
        if (list.size() > 0) {
            return (Map) list.get(0);
        }
        return null;
    }

    public static int[] batchUpdate(String sql, List<Object[]> listParams) {
        return BaseDao.getJdbcTemplate().batchUpdate(sql, listParams);
    }

    public static int updateInt(String sql, Object... obj) {
        return BaseDao.getJdbcTemplate().update(sql, obj);
    }

    public static boolean update(String sql, Object... obj) {
        return BaseDao.getJdbcTemplate().update(sql, obj) > 0;
    }

    public static List findForIn(String sqlForIn, Object... obj) {
        return findForIn(sqlForIn, "", obj);
    }

    public static List findForIn(String sqlForIn, String endSql, Object... obj) {
        String string = " " + CommonUtil.getWen(obj.length);
        return BaseDao.getJdbcTemplate().queryForList(sqlForIn + string + endSql, obj);
    }

    @SuppressWarnings("unchecked")
    public static boolean execute(final List sql, final List cs) {
        try {
            return BaseDao.getJdbcTemplate().execute(new ConnectionCallback<Boolean>() {
                public Boolean doInConnection(Connection conn) throws SQLException, DataAccessException {
                    conn.setAutoCommit(false);
                    PreparedStatement ps = null;
                    for (int i = 0; i < sql.size(); i++) {
                        ps = conn.prepareStatement(sql.get(i).toString());
                        Object[] obj = (Object[]) cs.get(i);
                        for (int j = 1; j <= obj.length; j++) {
                            ps.setObject(j, obj[j - 1]);
                        }
                        ps.executeUpdate();
                    }
                    ps.clearParameters();
                    conn.commit();
                    conn.setAutoCommit(true);
                    ps.close();
                    conn.close();
                    return true;
                }
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
            logger.error("Jdbc execute Exception", e);
            throw e;
        }
    }

    /**
     * 分页
     *
     * @param sql
     * @param pageNumber
     * @param pageSize
     * @param totalCount
     * @param obj
     * @return
     */
    public static Page findPage(String sql, Object pageNumber, Integer pageSize, Long totalCount, Object... obj) {
        return Jdbc.find(sql, pageNumber, pageSize, totalCount, obj);
    }

    /**
     * 适合单条sql 分页 最大条数计算 一般sql都可使用 如果条件中包涵sql不得使用
     *
     * @param sql
     * @param pageNumber
     * @param obj
     * @return
     */
    public static Page findPage(String sql, Object pageNumber, Object... obj) {
        return findPage(sql, pageNumber, pageSize, obj);
    }

    /**
     * 适合单条sql 分页 最大条数计算 一般sql都可使用 如果条件中包涵sql不得使用
     *
     * @param sql
     * @param pageNumber
     * @param pageSize
     * @param obj
     * @return
     */
    public static Page findPage(String sql, Object pageNumber, Object pageSize, Object... obj) {
        String loSql = sql.toLowerCase();
        int index = loSql.lastIndexOf("from");
        int indexOrder = loSql.lastIndexOf("order");
        if (index == -1) {
            return new Page();
        }
        String countSql = "select count(*) " + sql.substring(index, indexOrder == -1 ? sql.length() : indexOrder);
        long count = Jdbc.findLong(countSql, obj);
        return Jdbc.find(sql, pageNumber, pageSize, count, obj);
    }

    public static Page find(String sql, Object pageNumber, Object pageSize, Object totalCount, Object... obj) {
        if (pageNumber == null || pageNumber.equals("")) {
            pageNumber = "1";
        }
        int index = Integer.parseInt(pageNumber + "");
        int size = Integer.parseInt(pageSize + "");
        int count = Integer.parseInt(totalCount + "");

        if (count <= 0) {
            return new Page();
        }

        long totalPageCount = getTotalPageCount(count, size);
        if (index > totalPageCount) {
        }
        if (index < 1) {
            index = 1;
        }
        int startIndex = (index - 1) * size;

        String exeSql = sql + " limit " + startIndex + "," + size;
        List list = find(exeSql, obj);
        return new Page(list, count, index, size, totalPageCount);
    }

    /**
     * 取总页数.
     */
    private static long getTotalPageCount(long totalCount, int pageSize) {
        if (totalCount % pageSize == 0)
            return totalCount / pageSize;
        else
            return totalCount / pageSize + 1;
    }
}
