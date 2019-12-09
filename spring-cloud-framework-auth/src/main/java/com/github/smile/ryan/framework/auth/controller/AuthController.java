package com.github.smile.ryan.framework.auth.controller;

import com.github.smile.ryan.framework.auth.model.response.HttpResponse;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * 名称：AuthController
 * 描述：AuthController.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Slf4j
@RestController
public class AuthController {

  @Autowired
  private ConsumerTokenServices consumerTokenServices;

  @GetMapping("/user")
  public Principal user(Principal user) {
    return user;
  }

  @DeleteMapping("/revoke/token")
  public ResponseEntity revokeToken(String access_token) {
    boolean result = consumerTokenServices.revokeToken(access_token);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @GetMapping("/hasPermission")
  public HttpResponse<Boolean> hasPermission(HttpServletRequest request) {
    if (StringUtils
        .equalsIgnoreCase(request.getHeader("X-Strict-Strategy"), Boolean.toString(true))) {
      return HttpResponse.error(404, "资源不存在");
    }
    return HttpResponse.ok(true);
  }
}
