package com.lin.framework.plugin.security.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @since 1.0.0
 * @description 判断当前用户是否具有其中一种的权限（逗号分隔，表示“或”的关系）
 */
public class HasAnyPermissions extends PermissionTag {

    // 权限分隔符
    private static final String PERMISSION_NAMES_DELIMITER = ",";

    @Override
    protected boolean showTagBody(String permissionNames) {
        // 是否具有任一指定权限
        boolean hasAnyPermission = false;

        // 获取当前用户
        Subject currentUser = getSubject();

        if (currentUser != null) {
            // 当前用户拥有任何一个指定的权限
            for (String permissionName : permissionNames.split(PERMISSION_NAMES_DELIMITER)) {
                if (currentUser.isPermitted(permissionName.trim())) {
                    hasAnyPermission = true;
                    break;
                }
            }
        }

        return hasAnyPermission;
    }
}
