package com.lin.framework.helper;

import com.lin.framework.annotation.Aspect;
import com.lin.framework.proxy.AspectProxy;
import com.lin.framework.proxy.Proxy;
import com.lin.framework.proxy.ProxyManger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @since 1.0.0
 * @description 方法拦截助手类
 */
public final class AopHelper {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

    static {
        try {
            // 创建代理Map
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            // 获取目标类和代理对象列表的映射关系
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);

            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
                // 获取目标类和代理列表
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();

                // 使用目标类和代理列表创建代理
                Object proxy = ProxyManger.createProxy(targetClass, proxyList);

                // 给目标类设置代理对象
                BeanHelper.setBean(targetClass, proxy);
            }

        } catch (Exception e) {
            LOGGER.error("进行AOP失败", e);
        }
    }

    /**
     * 创建代理Map
     */
    private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
        HashMap<Class<?>, Set<Class<?>>> proxyMap = new HashMap<>();
        // 获取集成了AspectProxy类的类的集合
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);

        for (Class<?> proxyClass : proxyClassSet) {
            // 类中包含Aspect注解
            if (proxyClass.isAnnotationPresent(Aspect.class)) {
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);

                // 获取带Aspect注解的类的集合
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);

                // 存入代理Map中
                proxyMap.put(proxyClass, targetClassSet);
            }
        }

        return proxyMap;
    }

    /**
     * 获取带Aspect注解的类的集合
     * @param aspect 切面
     * @return 带Aspect注解的类的集合
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception {
        Set<Class<?>> targetClassSet = new HashSet<>();
        Class<? extends Annotation> annotation = aspect.value();

        // 注解非空，且注解不为Aspect
        if (annotation != null && !annotation.equals(Aspect.class)) {
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }

        return targetClassSet;
    }

    /**
     * 获取目标类和代理对象列表的映射关系
     * @param proxyMap 代理Map
     * @return 目标类和代理对象列表的映射关系
     */
    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();

        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()) {
            // 获取代理类和目标类集合
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();

            for (Class<?> targetClass : targetClassSet) {
                // 创建代理实例
                Proxy proxy = (Proxy) proxyClass.newInstance();

                if (targetMap.containsKey(targetClass)) {
                    // 目标Map中已包含目标类，将目标类存入已存在的List
                    targetMap.get(targetClass).add(proxy);
                } else {
                    // 目标Map中未包含目标类，新建List并存入目标Map中
                    List<Proxy> proxyList = new ArrayList<>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass, proxyList);
                }
            }
        }

        return targetMap;
    }


}
