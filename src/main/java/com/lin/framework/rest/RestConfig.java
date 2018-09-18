package com.lin.framework.rest;

import com.lin.framework.helper.ConfigHelper;
import com.lin.utils.StringUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @description 从配置文件中获取REST相关属性
 * @since 1.0.0
 */
public class RestConfig {

    /**
     * 是否开启REST日志记录
     */
    public static boolean isLog() {
        return ConfigHelper.getBoolean(RestConstant.LOG);
    }

    /**
     * 是否开启JSONP
     */
    public static boolean isJsonp() {
        return ConfigHelper.getBoolean(RestConstant.JSONP);
    }

    /**
     * 获取JSONP函数
     */
    public static String getJsonpFunction() {
        return ConfigHelper.getString(RestConstant.JSONP_FUNCTION);
    }

    /**
     * 是否开启CORS
     */
    public static boolean isCors() {
        return ConfigHelper.getBoolean(RestConstant.CORS);
    }

    /**
     * 获取CORS orgin列表
     */
    public static List<String> getCorsOriginList() {
        String corsOrigin = ConfigHelper.getString(RestConstant.CORS_ORIGIN);
        return Arrays.asList(StringUtil.splitString(corsOrigin, ","));
    }

}
