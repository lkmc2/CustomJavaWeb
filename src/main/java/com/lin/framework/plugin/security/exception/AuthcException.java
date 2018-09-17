package com.lin.framework.plugin.security.exception;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @since 1.0.0
 * @description 认证异常（当非法访问时抛出）
 */
public class AuthcException extends Exception {

    public AuthcException() {
        super();
    }

    public AuthcException(String message) {
        super(message);
    }

    public AuthcException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthcException(Throwable cause) {
        super(cause);
    }

}
