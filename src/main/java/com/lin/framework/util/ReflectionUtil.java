package com.lin.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 反射工具类
 */
public final class ReflectionUtil {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建实例
     * @param cls 实例类型
     * @return 实例对象
     */
    public static Object newInstance(Class<?> cls) {
        Object instance;

        try {
            // 实例化对象
            instance = cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("实例化对象失败", e);
            throw new RuntimeException(e);
        }

        return instance;
    }

    /**
     * 调用方法
     * @param obj 执行方法的对象
     * @param method 方法
     * @param args 方法参数
     * @return 方法执行结果
     */
    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result;

        try {
            method.setAccessible(true);
            // 调用方法并生成结果
            result = method.invoke(obj, args);
        } catch (Exception e) {
            LOGGER.error("调用方法失败", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 设置成员变量的值
     * @param obj 执行对象
     * @param field 成员变量
     * @param value 设定给变量的新值
     */
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
           LOGGER.error("设置变量值失败", e);
            throw new RuntimeException(e);
        }
    }

}
