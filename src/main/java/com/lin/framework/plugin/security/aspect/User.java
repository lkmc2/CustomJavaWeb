package com.lin.framework.plugin.security.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @since 1.0.0
 * @description 判断当前用户是否已登录（包括：已认证和已记住）
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface User {
}
