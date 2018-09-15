package com.lin.framework.helper;

import com.lin.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description Bean助手类
 */
public final class BeanHelper {

    // Bean映射（用于存放Bean类与Bean实例的映射关系）
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        // 获取应用包名下所有Bean类（包括：Service、Controller等）
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();

        for (Class<?> beanClass : beanClassSet) {
            // 实例化该类的对象
            Object obj = ReflectionUtil.newInstance(beanClass);
            // 将该类的对象放入Bean映射中
            BEAN_MAP.put(beanClass, obj);
        }
    }

    /**
     * 获取Bean映射
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     * @param cls Bean的类型
     * @param <T> Bean的泛型
     * @return 该Bean类的实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("无法找到该类的实例：" + cls);
        }

        return (T) BEAN_MAP.get(cls);
    }

    /**
     * 设置Bean实例
     */
    public static void setBean(Class<?> cls, Object obj) {
        BEAN_MAP.put(cls, obj);
    }

}
