package com.lin.helper;

import com.lin.utils.PropsUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
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
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("获取数据库连接失败", e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("关闭数据库连接失败", e);
            }
        }
    }

    /**
     * 查询实体列表
     * @param entityClass 实体类型
     * @param conn 数据库连接
     * @param sql 执行的sql
     * @param params sql对应的参数
     * @param <T> 实体泛型
     * @return 实体列表
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass,
                                              Connection conn,
                                              String sql,
                                              Object... params) {
        List<T> entityList;

        try {
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
        } catch (Exception e) {
            LOGGER.error("查询实体列表失败", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
        return entityList;
    }

}
