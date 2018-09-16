package demo.threadlocal.dbutil.service.impl;

import demo.threadlocal.dbutil.DBUtil;
import demo.threadlocal.dbutil.service.ProductService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lkmc2
 * @date 2018/9/16
 * @description 产品服务实现类
 */
public class ProductServiceImpl implements ProductService {

    // 更新产品信息sql
    private static final String UPDATE_PRODUCT_SQL = "update product set price = ? where id = ?";

    // 插入日志sql
    private static final String INSERT_LOG_SQL = "insert into log(created, description) values (?, ?)";

    @Override
    public void updateProductPrice(long productId, int price) {
        try {
            Connection conn = DBUtil.getConnection();
            // 关闭自动提交事务（开启事务）
            conn.setAutoCommit(false);

            // 更新产品信息
            updateProduct(conn, UPDATE_PRODUCT_SQL, productId, price);

            // 插入日志
            insertLog(conn, INSERT_LOG_SQL, "Update product.");

            // 提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            DBUtil.closeConnection();
        }
    }

    /**
     * 更新产品信息
     * @param conn 数据库连接
     * @param updateProductSql 更新产品sql
     * @param productId 产品id
     * @param productPrice 价格
     */
    private void updateProduct(Connection conn, String updateProductSql, long productId, int productPrice) throws SQLException {
        // 设置sql参数
        PreparedStatement pstmt = conn.prepareStatement(updateProductSql);
        pstmt.setInt(1, productPrice);
        pstmt.setLong(2, productId);

        // 执行sql，获取受影响行数
        int rows = pstmt.executeUpdate();

        if (rows > 0) {
            System.out.println("更新产品成功！");
        }
    }

    /**
     * 插入日志到数据库
     * @param conn 数据库连接
     * @param insertLogSql 插入日志sql
     * @param logDescription 日志信息
     */
    private void insertLog(Connection conn, String insertLogSql, String logDescription) throws SQLException {
        // 设置sql参数
        PreparedStatement pstmt = conn.prepareStatement(insertLogSql);
        pstmt.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        pstmt.setString(2, logDescription);

        // 执行sql，获取受影响行数
        int rows = pstmt.executeUpdate();

        if (rows > 0) {
            System.out.println("插入日志成功！");
        }
    }

}
