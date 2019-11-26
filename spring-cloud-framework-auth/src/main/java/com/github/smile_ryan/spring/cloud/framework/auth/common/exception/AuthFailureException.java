package com.github.smile_ryan.spring.cloud.framework.auth.common.exception;

/**
 * <pre>
 * 名称：AuthFailureException
 * 描述：AuthFailureException.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public class AuthFailureException extends RuntimeException {

    public AuthFailureException() {
        this("认证失败！");
    }

    public AuthFailureException(String message) {
        super(message);
    }
}
