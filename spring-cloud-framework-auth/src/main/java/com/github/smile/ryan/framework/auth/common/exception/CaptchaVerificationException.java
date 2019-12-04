package com.github.smile.ryan.framework.auth.common.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * <pre>
 * 名称：CaptchaVerificationException
 * 描述：CaptchaVerificationException.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public class CaptchaVerificationException extends AuthenticationException {

  public CaptchaVerificationException(String msg, Throwable t) {
    super(msg, t);
  }

  public CaptchaVerificationException(String msg) {
    super(msg);
  }
}
