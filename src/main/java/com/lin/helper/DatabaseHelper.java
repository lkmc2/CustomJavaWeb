package com.lin.helper;

import com.lin.utils.CollectionUtil;
import com.lin.utils.PropsUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @description 数据库操作助手类
 */
public final class DatabaseHelper {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    // 查询执行器
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    // 持有数据库连接的本地线程变量
    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<>();

    private static final String DRIVER; // 驱动名
    private static final String URL; // 数据库链接
    private static final String USERNAME; // 用户名
    private static final String PASSWORD; // 密码

    static {
        Properties conf = PropsUtil.loadProps("db.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("无法加载JDBC驱动", e);
        }
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        // 获取本地线程的数据库连接
        Connection conn = CONNECTION_HOLDER.get();

        // 数据库连接为空
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                LOGGER.error("获取数据库连接失败", e);
                throw new RuntimeException(e);
            } finally {
                // 设置数据库连接到本地线程
                CONNECTION_HOLDER.set(conn);
            }
        }

        return conn;
    }

    /**
     * 关闭数据库连接
     */
    public static void closeConnection() {
        // 获取本地线程的数据库连接
        Connection conn = CONNECTION_HOLDER.get();

        // 数据库连接不为空
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("关闭数据库连接失败", e);
                throw new RuntimeException(e);
            } finally {
                // 移除本地线程的数据库连接
                CONNECTION_HOLDER.remove();
            }
        }
    }

    /**
     * 查询实体列表
     * @param entityClass 实体类型
     * @param sql 执行的sql
     * @param params sql对应的参数
     * @param <T> 实体泛型
     * @return 实体列表
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList;

        try {
            // 获取数据库连接
            Connection conn = getConnection();
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
        } catch (Exception e) {
            LOGGER.error("查询实体列表失败", e);
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接
            closeConnection();
        }
        return entityList;
    }

    /**
     * 查询实体
     * @param entityClass 实体类型
     * @param sql 执行的sql
     * @param params sql对应的参数
     * @param <T> 实体泛型
     * @return 实体
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity;

        try {
            // 获取数据库连接
            Connection conn = getConnection();
            entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("查询实体失败", e);
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接
            closeConnection();
        }

        return entity;
    }

    /**
     * 执行查询语句（可连接多表查询）
     * @param sql 查询语句
     * @param params sql对应的参数
     * @return 查询结果
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
        List<Map<String, Object>> result;

        try {
            // 获取数据库连接
            Connection conn = getConnection();
            result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
        } catch (SQLException e) {
            LOGGER.error("执行查询失败", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 执行更新语句（包括update、insert、delete）
     * @param sql 执行的sql
     * @param params sql对应的参数
     * @return 受影响行数
     */
    public static int executeUpdate(String sql, Object... params) {
        // 受影响行数
        int rows = 0;

        try {
            // 获取数据库连接
            Connection conn = getConnection();
            rows = QUERY_RUNNER.update(conn, sql, params);
        } catch (SQLException e) {
            LOGGER.error("执行更新失败", e);
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接
            closeConnection();
        }
        return rows;
    }

    /**
     * 插入实体
     * @param entityClass 实体类型
     * @param fieldMap 对象信息
     * @param <T> 实体泛型
     * @return 是否插入成功
     */
    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
        // 对象信息为空，插入失败
        if (CollectionUtil.isEmpty(fieldMap)) {
            LOGGER.error("不能插入实体：fieldMap为空");
            return false;
        }

        String sql = "INSERT INTO " + getTableName(entityClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");

        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }

        // 将最后位置的逗号换成反括号
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");

        // 拼接执行的sql
        sql += columns + " VALUES " + values;

        LOGGER.info("当前执行的SQL为：" + sql);

        // 将对象信息的值转换成参数数组
        Object[] params = fieldMap.values().toArray();


        return executeUpdate(sql, params) == 1;
    }

    /**
     * 根据实体类型获取对应的数据库表名
     */
    private static <T> String getTableName(Class<T> entityClass) {
        return entityClass.getSimpleName();
    }

}
