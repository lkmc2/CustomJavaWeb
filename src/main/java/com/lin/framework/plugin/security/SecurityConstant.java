package com.lin.framework.plugin.security;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @since 1.0.0
 * @description 安全配置常量
 */
public interface SecurityConstant {

    String REALMS = "smart.plugin.security.realms";
    String REALMS_JDBC = "jdbc";
    String REALMS_CUSTOM = "custom";

    String SMART_SECURITY = "smart.plugin.security.custom.class";

    // 根据用户名获取密码
    String JDBC_AUTHC_QUERY = "smart.plugin.security.jdbc.authc_query";

    // 根据用户名获取角色名集合
    String JDBC_ROLES_QUERY = "smart.plugin.security.jdbc.roles_query";

    // 根据角色名获取权限名集合
    String JDBC_PERMISSIONS_QUERY = "smart.plugin.security.jdbc.permissions_query";

    // 是否进行缓存
    String CACHE = "smart.plugin.security.cache";

}
