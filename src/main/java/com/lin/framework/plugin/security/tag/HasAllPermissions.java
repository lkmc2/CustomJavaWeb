package com.lin.framework.plugin.security.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description 判断当前用户是否具有其中所有的权限（逗号分隔，表示“与”的关系）
 * @since 1.0.0
 */
public class HasAllPermissions extends PermissionTag {

    // 权限分隔符
    private static final String PERMISSION_NAMES_DELIMITER = ",";

    @Override
    protected boolean showTagBody(String permissionNames) {
        // 是否具有所有指定权限
        boolean hasAllPermission = false;

        // 获取当前用户
        Subject currentUser = getSubject();

        if (currentUser != null) {
            // 当前用户拥有所有指定的权限
            if (currentUser.isPermittedAll(permissionNames.split(PERMISSION_NAMES_DELIMITER))) {
                hasAllPermission = true;
            }
        }

        return hasAllPermission;
    }
}
