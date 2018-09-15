package com.lin.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @description 字符串工具类
 */
public final class StringUtil {

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }

        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否非空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


    /**
     * 切割字符串
     */
    public static String[] splitString(String source, String splitWord) {
        return source.split(splitWord);
    }

}
