package com.lin.framework.aspect;

import com.lin.framework.proxy.AspectProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @since 1.0.0
 * @description 拦截Controller的所有方法
 */
public class ControllerAspect extends AspectProxy {

    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    // 记录开始时间
    private long begin;

    @Override
    public void before(Class<?> targetClass, Method targetMethod, Object[] methodParams) {
        LOGGER.debug("-------- begin --------");
        LOGGER.debug(String.format("class: %s", targetClass.getName()));
        LOGGER.debug(String.format("method: %s", targetMethod.getName()));

        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> targetClass, Method targetMethod, Object[] methodParams, Object result) {
        LOGGER.debug(String.format("time: %dms", System.currentTimeMillis() - begin));
        LOGGER.debug("-------- end --------");
    }

}
