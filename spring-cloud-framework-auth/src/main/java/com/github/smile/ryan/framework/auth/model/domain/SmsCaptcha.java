package com.github.smile.ryan.framework.auth.model.domain;

import lombok.Data;

/**
 * <pre>
 * 名称：SmsCaptchaService
 * 描述：SmsCaptchaService.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
public class SmsCaptcha {

  private String code;
  private Integer expirationTime;

  public SmsCaptcha() {
  }


  public SmsCaptcha(String code, Integer expirationTime) {
    this.code = code;
    this.expirationTime = expirationTime;
  }
}
