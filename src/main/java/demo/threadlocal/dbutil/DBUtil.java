package demo.threadlocal.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 数据库工具类
 */
public class DBUtil {

    // 数据库配置
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/custom_java_web_test?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // 存放数据库连接的局部线程变量（使每个线程都拥有自己的连接）
    private static ThreadLocal<Connection> connContainer = new ThreadLocal<>();

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        // 获取数据库连接
        Connection conn = connContainer.get();

        try {
            // 获取的连接为空，重新创建连接
            if (conn == null) {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 设置连接到线程变量中
            connContainer.set(conn);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     */
    public static void closeConnection() {
        // 获取数据库连接
        Connection conn = connContainer.get();

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 移除数据库连接
            connContainer.remove();
        }
    }

}
