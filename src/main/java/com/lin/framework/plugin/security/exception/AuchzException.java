package com.lin.framework.plugin.security.exception;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @since 1.0.0
 * @description 授权异常（当权限无效时抛出）
 */
public class AuchzException extends RuntimeException {

    public AuchzException() {
        super();
    }

    public AuchzException(String message) {
        super(message);
    }

    public AuchzException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuchzException(Throwable cause) {
        super(cause);
    }

}
