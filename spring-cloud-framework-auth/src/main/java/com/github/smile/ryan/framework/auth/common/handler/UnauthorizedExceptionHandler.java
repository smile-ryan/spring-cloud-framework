package com.github.smile.ryan.framework.auth.common.handler;

import com.github.smile.ryan.framework.auth.common.util.HttpUtils;
import com.github.smile.ryan.framework.auth.model.response.HttpResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 名称：AuthExceptionEntryPoint
 * 描述：AuthExceptionEntryPoint.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Component
public class UnauthorizedExceptionHandler extends OAuth2AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException e) throws IOException {
    HttpUtils
        .writerError(HttpResponse.error(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), response);
  }

}
