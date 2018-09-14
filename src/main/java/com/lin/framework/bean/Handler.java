package com.lin.framework.bean;

import java.lang.reflect.Method;

/**
 * @author lkmc2
 * @date 2018/9/14
 * @since 1.0.0
 * @description 封装Action信息
 */
public class Handler {

    // Controller类
    private Class<?> controllerClass;

    // Action方法
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

}
