package com.lin.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @since 1.0.0
 * @description 代理管理器
 */
public class ProxyManger {

    /**
     * 创建代理对象
     * @param targetClass 目标类型
     * @param proxyList 代理列表
     * @param <T> 返回结果泛型
     * @return 创建后的代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList) {
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
                // 执行并返回代理链
                return new ProxyChain(
                        targetClass,
                        targetObject,
                        targetMethod,
                        methodProxy,
                        methodParams,
                        proxyList
                ).doProxyChain();
            }
        });
    }

}
