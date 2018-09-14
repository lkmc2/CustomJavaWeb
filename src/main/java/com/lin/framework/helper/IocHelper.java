package com.lin.framework.helper;

import com.lin.framework.annotation.Inject;
import com.lin.framework.util.ArrayUtil;
import com.lin.framework.util.ReflectionUtil;
import com.lin.utils.CollectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 依赖注入助手类
 */
public final class IocHelper {

    static {
        // 获取所有的Bean类与Bean实例之间的映射关系（简称Bean Map）
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();

        if (CollectionUtil.isNotEmpty(beanMap)) {
            // 遍历Bean Map
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                // 从BeanMap中取出Bean类与Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();

                // 获取Bean类定义的所有成员变量（简称Bean Field）
                Field[] beanFields = beanClass.getDeclaredFields();

                if (ArrayUtil.isNotEmpty(beanFields)) {
                    // 遍历Bean Field
                    for (Field beanField : beanFields) {
                        // 判断当前Bean Field是否带带有Inject注解
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldClass = beanField.getType();
                            // 获取Bean Map中存储的bean实例
                            Object beanFieldInstance = beanMap.get(beanFieldClass);

                            if (beanFieldInstance != null) {
                                // 通过反射初始化BeanField的值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }

}
