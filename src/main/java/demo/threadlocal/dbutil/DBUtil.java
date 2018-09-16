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

    // 数据库连接
    private static Connection conn = null;

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     */
    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
