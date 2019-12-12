package com.github.smile.ryan.framework.auth.common.exception;

/**
 * <pre>
 * 名称：ArgumentsFailureException
 * 描述：ArgumentsFailureException.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public class ArgumentsFailureException extends RuntimeException {

    public ArgumentsFailureException() {
        this("参数错误");
    }

    public ArgumentsFailureException(String message) {
        super(message);
    }
}
