package com.lin.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @since 1.0.0
 * @description 代理链
 */
public class ProxyChain {

    private final Class<?> targetClass; // 目标类
    private final Object targetObject; // 目标对象
    private final Method targetMethod; // 目标方法
    private final MethodProxy methodProxy; // 方法代理
    private final Object[] methodParams; // 方法参数

    private final List<Proxy> proxyList; // 代理列表

    private int proxyIndex = 0; // 代理元素下标

   public ProxyChain(Class<?> targetClass,
                      Object targetObject,
                      Method targetMethod,
                      MethodProxy methodProxy,
                      Object[] methodParams,
                      List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    /**
     * 调用执行链
     * @return 方法执行结果
     */
    public Object doProxyChain() throws Throwable {
        Object methodResult;

        if (proxyIndex < proxyList.size()) {
            // 代理元素下标小于代理列表，从代理列表中获取方法执行
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        } else {
            // 其他情况，执行目标对象的业务逻辑
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }

        return methodResult;
    }

}
