package com.lin.framework.proxy;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @since 1.0.0
 * @description 代理接口
 */
public interface Proxy {
    /**
     * 执行链式代理
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
