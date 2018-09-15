package com.lin.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @since 1.0.0
 * @description 切面代理（抽象类）
 */
public abstract class AspectProxy implements Proxy {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public final Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;

        Class<?> targetClass = proxyChain.getTargetClass();
        Method targetMethod = proxyChain.getTargetMethod();
        Object[] methodParams = proxyChain.getMethodParams();

        begin();

        try {
            if (intercept(targetClass, targetMethod, methodParams)) {
                before(targetClass, targetMethod, methodParams);
                result = proxyChain.doProxyChain();
                after(targetClass, targetMethod, methodParams, result);
            } else {
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            LOGGER.error("代理异常", e);
            error(targetClass, targetMethod, methodParams, e);
            throw e;
        } finally {
            end();
        }

        return result;
    }

    /**
     * 执行开始逻辑
     */
    public void begin() {
    }

    /**
     * 执行拦截逻辑
     */
    public boolean intercept(Class<?> targetClass, Method targetMethod, Object[] methodParams) {
        return false;
    }

    /**
     * 在业务代码执行前的逻辑
     */
    public void before(Class<?> targetClass, Method targetMethod, Object[] methodParams) {
    }

    /**
     * 在业务代码执行后的逻辑
     */
    public void after(Class<?> targetClass, Method targetMethod, Object[] methodParams, Object result) {
    }

    /**
     * 发生异常时的逻辑
     */
    public void error(Class<?> targetClass, Method targetMethod, Object[] methodParams, Exception e) {
    }

    /**
     * 执行结束逻辑
     */
    public void end() {
    }

}
