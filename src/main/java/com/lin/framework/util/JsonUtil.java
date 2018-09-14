package com.lin.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description JSON工具类
 */
public final class JsonUtil {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    // JSON转换器
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    /**
     * 将POJO转换成JSON
     * @param obj 将转换的对象
     * @param <T> 对象泛型
     * @return json数据
     */
    public static <T> String toJson(T obj) {
        String json;

        try {
            // 将对象转换成字符串
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("POJO转换JSON失败", e);
            throw new RuntimeException(e);
        }

        return json;
    }

    /**
     * 将JSON转换成POJO
     * @param json json数据
     * @param type POJO对象类型
     * @param <T> POJO对象的泛型
     * @return POJO对象
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;

        try {
            // 将字符串转换成对象
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            LOGGER.error("JSON转换POJO失败", e);
            throw new RuntimeException(e);
        }

        return pojo;
    }
}
