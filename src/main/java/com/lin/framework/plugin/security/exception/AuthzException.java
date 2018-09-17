package com.lin.framework.plugin.security.exception;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @since 1.0.0
 * @description 授权异常（当权限无效时抛出）
 */
public class AuthzException extends RuntimeException {

    public AuthzException() {
        super();
    }

    public AuthzException(String message) {
        super(message);
    }

    public AuthzException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthzException(Throwable cause) {
        super(cause);
    }

}
