package com.lin.framework.proxy;

import com.lin.framework.annotation.Transaction;
import com.lin.framework.helper.DatabaseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author lkmc2
 * @date 2018/9/16
 * @since 1.0.0
 * @description 事务代理
 */
public class TransactionProxy implements Proxy {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);

    // 事务是否开始标志位
    private static final ThreadLocal<Boolean> FLAG_HOLDER = ThreadLocal.withInitial(() -> false);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;

        // 事务是否开始标志位
        boolean flag = FLAG_HOLDER.get();
        // 目标方法
        Method method = proxyChain.getTargetMethod();

        // 事务未开始，并且目标方法带有Transaction注解
        if (!flag && method.isAnnotationPresent(Transaction.class)) {
            // 设置标志位为事务开始
            FLAG_HOLDER.set(true);

            try {
                DatabaseHelper.beginTransaction();
                LOGGER.debug("开启事务");

                // 调用执行链，获取方法执行结果
                result = proxyChain.doProxyChain();

                DatabaseHelper.commitTransaction();
                LOGGER.debug("提交事务");
            } catch (Exception e) {
                DatabaseHelper.rollbackTransaction();
                LOGGER.debug("事务回滚");
                throw e;
            } finally {
                // 移除事务是否开始标志位
                FLAG_HOLDER.remove();
            }
        } else {
            // 事务已开始
            // 调用执行链，获取方法执行结果
            result = proxyChain.doProxyChain();
        }

        return result;
    }

}
