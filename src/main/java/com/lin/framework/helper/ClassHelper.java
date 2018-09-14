package com.lin.framework.helper;

import com.lin.framework.annotation.Controller;
import com.lin.framework.annotation.Service;
import com.lin.framework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 类操作助手类
 */
public final class ClassHelper {
    // 类集合（用于存放所加载的类）
    private static final Set<Class<?>> CLASS_SET;

    static {
        // 获取应用基础包名
        String basePackage = ConfigHelper.getAppBasePackage();

        // 获取指定包名下的所有类
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取指定包名下的所有类
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 获取应用包名下所有Service类
     */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<>();

        for (Class<?> cls : CLASS_SET) {
            // 包含有Service注解的类都加入新集合中
            if (cls.isAnnotationPresent(Service.class)) {
                classSet.add(cls);
            }
        }

        return classSet;
    }

    /**
     * 获取应用包名下所有Controller类
     */
    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet = new HashSet<>();

        for (Class<?> cls : CLASS_SET) {
            // 包含有Controller注解的类都加入新集合中
            if (cls.isAnnotationPresent(Controller.class)) {
                classSet.add(cls);
            }
        }

        return classSet;
    }

    /**
     * 获取应用包名下所有Bean类（包括：Service、Controller等）
     */
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet = new HashSet<>();

        // 获取应用包名下所有Service类放入新集合
        beanClassSet.addAll(getServiceClassSet());

        // 获取应用包名下所有Controller类放入新集合
        beanClassSet.addAll(getControllerClassSet());

        return beanClassSet;
    }

}
