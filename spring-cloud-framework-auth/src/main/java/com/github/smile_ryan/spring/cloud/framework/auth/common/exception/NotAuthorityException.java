package com.github.smile_ryan.spring.cloud.framework.auth.common.exception;

/**
 * <pre>
 * 名称：NotAuthorityException
 * 描述：NotAuthorityException.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public class NotAuthorityException extends RuntimeException {

    public NotAuthorityException() {
        this("没有权限！");
    }

    public NotAuthorityException(String message) {
        super(message);
    }
}
