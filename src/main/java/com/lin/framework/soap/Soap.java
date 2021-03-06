package com.lin.framework.soap;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @since 1.0.0
 * @description SOAP服务注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Soap {
    /**
     * 服务名
     */
    String value() default "";
}
