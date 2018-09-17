package com.lin.framework.plugin.security.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.RoleTag;

import java.util.Arrays;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @since 1.0.0
 * @description 判断当前用户是否具有其中所有的角色（逗号分隔，表示“与”的关系）
 */
public class HasAllRolesTag extends RoleTag {

    // 角色分隔符
    private static final String ROLE_NAMES_DELIMITER = ",";

    @Override
    protected boolean showTagBody(String roleNames) {
        // 是否具有所有角色
        boolean hasAllRole = false;

        // 获取当前用户
        Subject currentUser = getSubject();

        if (currentUser != null) {
            // 当前用户拥有所有指定的角色
            if (currentUser.hasAllRoles(Arrays.asList(roleNames.split(ROLE_NAMES_DELIMITER)))) {
                hasAllRole = true;
            }
        }

        return hasAllRole;
    }
}
