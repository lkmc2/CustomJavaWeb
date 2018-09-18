package com.lin.framework.soap;

import com.lin.framework.helper.ConfigHelper;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @since 1.0.0
 * @description 从配置文件中获取SOAP相关属性
 */
public final class SoapConfig {

    /**
     * 是否记录日志信息
     */
    public static boolean isLog() {
        return ConfigHelper.getBoolean(SoapConstant.LOG);
    }

}
