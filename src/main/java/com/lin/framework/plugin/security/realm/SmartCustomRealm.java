package com.lin.framework.plugin.security.realm;

import com.lin.framework.plugin.security.SecurityConstant;
import com.lin.framework.plugin.security.SmartSecurity;
import com.lin.framework.plugin.security.password.Md5CredentialsMatcher;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @since 1.0.0
 * @description 自定义Realm
 */
public class SmartCustomRealm extends AuthorizingRealm {

    // 安全接口
    private final SmartSecurity smartSecurity;

    public SmartCustomRealm(SmartSecurity smartSecurity) {
        this.smartSecurity = smartSecurity;
        // 设置Realm的名字
        super.setName(SecurityConstant.REALMS_CUSTOM);
        // 设置MD5加密算法
        super.setCredentialsMatcher(new Md5CredentialsMatcher());
    }

    // 授权（权限验证）
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        if (principal == null) {
            throw new AuthorizationException("凭证参数为空");
        }

        // 获取已认证用户的用户名
        String username = (String) super.getAvailablePrincipal(principal);

        // 通过SmartSecurity安全接口，根据用户名获取角色名集合
        Set<String> roleNameSet = smartSecurity.getRoleNameSet(username);

        // 通过SmartSecurity安全接口，并根据角色名获取其对应的权限名集合
        HashSet<String> permissionNameSet = new HashSet<>();

        if (roleNameSet != null && roleNameSet.size() > 0) {
            for (String roleName : roleNameSet) {
                Set<String> currentPermissionNameSet = smartSecurity.getPermissionNameSet(roleName);
                permissionNameSet.addAll(currentPermissionNameSet);
            }
        }

        // 将角色名集合和权限名集合放入AuthorizationInfo对象（授权信息），便于后续的授权操作
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleNameSet);
        authorizationInfo.setStringPermissions(permissionNameSet);

        return authorizationInfo;
    }

    // 认证（登录）
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token == null) {
            throw new AuthenticationException("token参数为空");
        }

        // 通过AuthenticationToken对象获取从表单中提交过来的用户名
        String username = ((UsernamePasswordToken) token).getUsername();

        // 通过SmartSecurity接口，并根据用户名获取数据库中存放的密码
        String password = smartSecurity.getPassword(username);

        // 将用户名和密码放入AuthenticationInfo对象（认证信息），便于后续的认证操作
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();

        // 存放用户名
        authenticationInfo.setPrincipals(new SimplePrincipalCollection(username, super.getName()));
        // 存放密码
        authenticationInfo.setCredentials(password);

        return authenticationInfo;
    }

}
