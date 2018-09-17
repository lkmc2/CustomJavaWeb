package com.lin.framework.plugin.security;

import java.util.Set;

/**
 * @author lkmc2
 * @date 2018/9/16
 * @since 1.0.0
 * @description 安全接口
 * 可在应用中实现该接口，或者在 smart.properties 文件中提供以下基于SQSL的配置项：
 * 1.smart.plugin.security.jdbc.authc_query：根据用户名获取密码
 * 2.smart.plugin.security.jdbc.roles_query：根据用户名获取角色名集合
 * 3.smart.plugin.security.jdbc.permissions_query：根据角色名获取权限名集合
 */
public interface SmartSecurity {

    /**
     * 根据用户名获取密码
     * @param username 用户名
     * @return 密码
     */
    String getPassword(String username);

    /**
     * 根据用户名获取角色名集合
     * @param username 用户名
     * @return 角色名集合
     */
    Set<String> getRoleNameSet(String username);

    /**
     * 根据角色名获取权限名集合
     * @param roleName 角色名
     * @return 权限名集合
     */
    Set<String> getPermissionNameSet(String roleName);

}
