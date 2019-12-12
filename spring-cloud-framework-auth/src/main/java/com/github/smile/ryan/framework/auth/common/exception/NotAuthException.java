package com.github.smile.ryan.framework.auth.common.exception;

/**
 * <pre>
 * 名称：NotAuthException
 * 描述：NotAuthException.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public class NotAuthException extends RuntimeException {

    public NotAuthException() {
        this("没有认证！");
    }

    public NotAuthException(String message) {
        super(message);
    }
}
