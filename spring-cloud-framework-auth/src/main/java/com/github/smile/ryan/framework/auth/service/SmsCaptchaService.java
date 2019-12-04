package com.github.smile.ryan.framework.auth.service;

import com.github.smile.ryan.framework.auth.model.domain.SmsCaptcha;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * <pre>
 * 名称：SmsCaptchaService
 * 描述：SmsCaptchaService.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public interface SmsCaptchaService {

  SmsCaptcha generator(ServletWebRequest request);

}
