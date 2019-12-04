package com.github.smile.ryan.framework.auth.service.impl;

import com.github.smile.ryan.framework.auth.common.properties.AuthSecurityProperties;
import com.github.smile.ryan.framework.auth.model.domain.SmsCaptcha;
import com.github.smile.ryan.framework.auth.service.SmsCaptchaService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * <pre>
 * 名称：SmsCaptchaServiceImpl
 * 描述：SmsCaptchaServiceImpl.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Component
public class SmsCaptchaServiceImpl implements SmsCaptchaService {

  @Autowired
  private AuthSecurityProperties authSecurityProperties;

  @Override
  public SmsCaptcha generator(ServletWebRequest request) {
    String smsCode = RandomStringUtils.randomNumeric(authSecurityProperties.getSms().getLength());
    return new SmsCaptcha(smsCode, authSecurityProperties.getSms().getExpirationTime());
  }


}
