package com.lin.framework.plugin.security.aspect;

import com.lin.framework.annotation.Aspect;
import com.lin.framework.annotation.Controller;
import com.lin.framework.plugin.security.exception.AuthzException;
import com.lin.framework.proxy.AspectProxy;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @since 1.0.0
 * @description 授权注解切面
 */
@Aspect(Controller.class)
public class AuthzAnnotationAspect extends AspectProxy {

    // 定义一个基于授权功能的注解类数组
    private static final Class[] ANNOTATION_CLASS_ARRAY = {
            User.class
    };

    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        // 从目标类与目标方法中获取相应的注解
        Annotation annotation = getAnnotation(cls, method);

        if (annotation != null) {
            // 获取注解类型
            Class<?> annotationType = annotation.annotationType();

            //  注解类型为User
           if (annotationType.equals(User.class)) {
               // 处理用户信息
                hanleUser();
            }
        }
    }

    /**
     * 从目标类与目标方法中获取相应的注解
     * @param cls 目标类
     * @param method 目标方法
     * @return 对应的注解
     */
    @SuppressWarnings("unchecked")
    private Annotation getAnnotation(Class<?> cls, Method method) {

        for (Class<? extends Annotation> annotationClass : ANNOTATION_CLASS_ARRAY) {
            // 首先判断目标方法上是否带有授权注解
            if (method.isAnnotationPresent(annotationClass)) {
                return method.getAnnotation(annotationClass);
            }

            // 然后判断目标类上是否带有授权注解
            if (cls.isAnnotationPresent(annotationClass)) {
                return cls.getAnnotation(annotationClass);
            }
        }

        // 若目标方法与目标类上均未带有授权注解，则返回空对象
        return null;
    }

    /**
     * 处理用户信息
     */
    private void hanleUser() {
        // 获取当前用户
        Subject currentUser = SecurityUtils.getSubject();

        // 获取用户凭证信息
        PrincipalCollection principals = currentUser.getPrincipals();

        if (principals == null || principals.isEmpty()) {
            throw new AuthzException("当前用户未登录");
        }
    }
}
