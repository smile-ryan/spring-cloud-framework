package com.github.smile.ryan.framework.auth.common.handler;

import com.github.smile.ryan.framework.auth.common.util.HttpUtils;
import com.github.smile.ryan.framework.auth.model.response.HttpResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 名称：LoginFailureHandler
 * 描述：LoginFailureHandler.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    HttpUtils.writerError(HttpResponse.error(401, exception.getMessage()), response);
  }
}
