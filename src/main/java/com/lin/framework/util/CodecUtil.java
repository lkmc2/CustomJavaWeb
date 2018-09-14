package com.lin.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 编码和解码操作工具类
 */
public final class CodecUtil {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 将URL编码
     */
    public static String encodeURL(String source) {
        String target;

        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("编码URL失败", e);
            throw new RuntimeException(e);
        }

        return target;
    }

    /**
     * 将URL解码
     */
    public static String decodeURL(String source) {
        String target;

        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("解码URL失败", e);
            throw new RuntimeException(e);
        }

        return target;
    }
}
