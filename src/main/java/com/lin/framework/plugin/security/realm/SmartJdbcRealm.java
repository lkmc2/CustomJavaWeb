package com.lin.framework.plugin.security.realm;

import com.lin.framework.helper.DatabaseHelper;
import com.lin.framework.plugin.security.SecurityConfig;

import com.lin.framework.plugin.security.password.Md5CredentialsMatcher;
import org.apache.shiro.realm.jdbc.JdbcRealm;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description JDBC Realm（需要提供相关 smart.framework.jdbc.* 配置项）
 */
public class SmartJdbcRealm extends JdbcRealm {

    public SmartJdbcRealm() {
        // 设置数据源
        super.setDataSource(DatabaseHelper.getDataSource());

        // 设置根据用户名获取密码的SQL
        super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());

        // 设置根据用户名获取角色名集合的SQL
        super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());

        // 设置根据角色名获取权限名集合的SQL
        super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());

        // 设置检查权限
        super.setPermissionsLookupEnabled(true);

        // 使用MD5加密算法
        super.setCredentialsMatcher(new Md5CredentialsMatcher());
    }

}
