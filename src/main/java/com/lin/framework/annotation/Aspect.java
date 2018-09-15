package com.lin.framework.annotation;

import java.lang.annotation.*;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @since 1.0.0
 * @description 切面注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 注解
     */
    Class<? extends Annotation> value();
}
