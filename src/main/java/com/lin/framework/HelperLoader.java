package com.lin.framework;

import com.lin.framework.helper.BeanHelper;
import com.lin.framework.helper.ClassHelper;
import com.lin.framework.helper.ControllerHelper;
import com.lin.framework.helper.IocHelper;
import com.lin.framework.util.ClassUtil;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 加载相应的Helper类
 */
public final class HelperLoader {

    /**
     * 初始化相关Helper类
     */
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for (Class<?> cls : classList) {
            // 加载类
            ClassUtil.loadClass(cls.getName());
        }
    }

}
