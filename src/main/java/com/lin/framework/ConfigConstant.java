package com.lin.framework;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @description 提供相关配置项常量
 */
public interface ConfigConstant {
    String CONFIG_FILE = "smart.properties"; // 配置文件名

    String JDBC_DRIVER = "smart.framework.jdbc.driver"; // 数据库驱动
    String JDBC_URL = "smart.framework.jdbc.url"; // 数据库地址
    String JDBC_USERNAME = "smart.framework.jdbc.username"; // 数据库用户名
    String JDBC_PASSWORD = "smart.framework.jdbc.password"; // 数据库密码

    String APP_BASE_PACKAGE = "smart.framework.app.base_package"; // 应用基础包名
    String APP_JSP_PATH = "smart.framework.app.jsp_path"; // 应用JSP路径
    String APP_ASSET_PATH = "smart.framework.app.asset_path"; // 应用静态资源路径
    String APP_UPLOAD_LIMIT = "smart.framework.app.upload_limit"; // 应用文件上传限制
}
