package com.lin.helper;

import com.lin.utils.CollectionUtil;
import com.lin.utils.PropsUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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

    // 持有数据库连接的本地线程变量
    private static final ThreadLocal<Connection> CONNECTION_HOLDER;

    // 查询执行器
    private static final QueryRunner QUERY_RUNNER;

    // 数据库连接池
    private static final BasicDataSource DATA_SOURCE;


    static {
        CONNECTION_HOLDER = new ThreadLocal<>();
        QUERY_RUNNER = new QueryRunner();

        Properties conf = PropsUtil.loadProps("db.properties");
        String driver = conf.getProperty("jdbc.driver");
        String url = conf.getProperty("jdbc.url");
        String username = conf.getProperty("jdbc.username");
        String password = conf.getProperty("jdbc.password");

        // 设置数据库连接
        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(driver);
        DATA_SOURCE.setUrl(url);
        DATA_SOURCE.setUsername(username);
        DATA_SOURCE.setPassword(password);
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
                // 获取数据库连接
                conn = DATA_SOURCE.getConnection();
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

        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }

        // 将最后位置的逗号换成空格
        columns.replace(columns.lastIndexOf(", "), columns.length(), "");
        values.replace(values.lastIndexOf(", "), values.length(), "");

        // 将执行的sql
        String sql = String.format("INSERT INTO %s(%s) VALUES (%s)", getTableName(entityClass), columns, values);

        // 将对象信息的值转换成参数数组
        Object[] params = fieldMap.values().toArray();

        // 打印sql信息
        logSqlInfo(sql, params);

        return executeUpdate(sql, params) == 1;
    }

    /**
     * 更新实体
     * @param entityClass 实体类型
     * @param id 实体id
     * @param fieldMap 对象信息
     * @param <T> 实体泛型
     * @return 是否更新成功
     */
    public static <T> boolean updateEntity(Class<T> entityClass,
                                           long id,
                                           Map<String, Object> fieldMap) {
        // 对象信息为空，插入失败
        if (CollectionUtil.isEmpty(fieldMap)) {
            LOGGER.error("不能插入实体：fieldMap为空");
            return false;
        }

        StringBuilder columns = new StringBuilder();

        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(" = ?, ");
        }

        // 将执行的sql
        String sql = String.format("UPDATE %s SET %s WHERE id = ?",
                getTableName(entityClass),
                columns.substring(0, columns.lastIndexOf(", ")));

        List<Object> paramsList = new ArrayList<>(fieldMap.values());
        paramsList.add(id);

        Object[] params = paramsList.toArray();

        // 打印sql信息
        logSqlInfo(sql, params);

        return executeUpdate(sql, params) == 1;
    }

    /**
     * 删除实体
     * @param entityClass 实体类型
     * @param id 实体id
     * @param <T> 实体泛型
     * @return 是否删除成功
     */
    public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
        String sql = String.format("DELETE FROM %s WHERE id = ?", getTableName(entityClass));

        // 打印sql信息
        logSqlInfo(sql, id);

        return executeUpdate(sql, id) == 1;
    }

    /**
     * 打印sql信息
     * @param sql 执行的sql
     * @param values 参数值
     */
    private static void logSqlInfo(String sql, Object... values) {
        for (Object value : values) {
            String result = String.valueOf(value);

            if (value instanceof String) {
                result = String.format("'%s'", result);
            }

            sql = sql.replaceFirst("\\?", result);
        }

        LOGGER.info("当前执行的SQL为：" + sql);
    }

    /**
     * 根据实体类型获取对应的数据库表名
     */
    private static <T> String getTableName(Class<T> entityClass) {
        return entityClass.getSimpleName();
    }

    /**
     * 执行sql文件
     * @param filePath sql文件路径
     */
    public static void executeSqlFile(String filePath) {
        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr)) {

            String sql;
            // 执行文件中的每一条sql
            while ((sql = reader.readLine()) != null) {
                if (sql.isEmpty()) {
                    continue;
                }
                DatabaseHelper.executeUpdate(sql);
            }
        } catch (IOException e) {
            LOGGER.error("执行SQL文件失败", e);
            throw new RuntimeException(e);
        }
    }

}
