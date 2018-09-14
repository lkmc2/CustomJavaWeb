package com.lin.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 数据工具类
 */
public final class ArrayUtil {

    /**
     * 判断数组是否非空
     */
    public static boolean isNotEmpty(Object[] array) {
        return ArrayUtils.isNotEmpty(array);
    }

    /**
     * 判断数组是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }

}
