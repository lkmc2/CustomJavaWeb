package com.lin.framework.helper;

import com.lin.framework.plugin.security.exception.AuthcException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @since 1.0.0
 * @description 安全助手类
 */
public final class SecurityHelper {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityHelper.class);

    /**
     * 登陆
     */
    public static void login(String username, String password) throws AuthcException {
        // 获取当前用户
        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser != null) {
            // 使用用户名和密码生成登陆凭据
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            try {
                // 使用登陆凭据登陆当前用户
                currentUser.login(token);
            } catch (AuthenticationException e) {
                LOGGER.error("登陆失败", e);
                throw new AuthcException(e);
            }
        }
    }

    /**
     * 注销
     */
    public static void logout() {
        // 获取当前用户
        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser != null) {
            // 注销当前登陆用户
            currentUser.logout();
        }
    }

}
