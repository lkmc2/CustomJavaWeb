package com.lin.framework.plugin.security;

import com.lin.framework.helper.ConfigHelper;
import com.lin.framework.util.ReflectionUtil;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @since 1.0.0
 * @description 从配置文件中获取安全属性
 */
public final class SecurityConfig {

    /**
     * 获取Realms的认证方式
     */
    public static String getRealms() {
        return ConfigHelper.getString(SecurityConstant.REALMS);
    }

    /**
     * 获取实现了安全接口的类
     */
    public static SmartSecurity getSmartSecurity() {
        String className = ConfigHelper.getString(SecurityConstant.SMART_SECURITY);
        return (SmartSecurity) ReflectionUtil.newInstance(className);
    }

    /**
     * 根据用户名获取密码
     */
    public static String getJdbcAuthcQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_AUTHC_QUERY);
    }

    /**
     * 根据用户名获取角色名集合
     */
    public static String getJdbcRolesQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_ROLES_QUERY);
    }

    /**
     * 根据角色名获取权限名集合
     */
    public static String getJdbcPermissionsQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_PERMISSIONS_QUERY);
    }

    /**
     * 是否进行缓存
     */
    public static boolean isCache() {
        return ConfigHelper.getBoolean(SecurityConstant.CACHE);
    }

}
