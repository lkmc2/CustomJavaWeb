package com.lin.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 流操作工具类
 */
public final class StreamUtil {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 从输入流中获取字符串
     * @param is 输入流
     * @return 输入流中的字符串
     */
    public static String getString(ServletInputStream is) {
        StringBuilder sb = new StringBuilder();

        try (InputStreamReader isr = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(isr)) {
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("获取字符串失败", e);
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
