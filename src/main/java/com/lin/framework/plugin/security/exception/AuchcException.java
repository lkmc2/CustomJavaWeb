package com.lin.framework.plugin.security.exception;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @since 1.0.0
 * @description 认证异常（当非法访问时抛出）
 */
public class AuchcException extends Exception {

    public AuchcException() {
        super();
    }

    public AuchcException(String message) {
        super(message);
    }

    public AuchcException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuchcException(Throwable cause) {
        super(cause);
    }

}
