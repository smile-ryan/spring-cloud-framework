package com.github.smile.ryan.framework.auth.web.controller;

import com.github.smile.ryan.framework.auth.model.response.HttpResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * 名称：TestController
 * 描述：TestController.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

  @GetMapping
  public String test() {
    return "test";
  }

  @PreAuthorize("#oauth2.hasScope('all')")
  @GetMapping("/message")
  public HttpResponse<String> message() {
    return HttpResponse.ok("燃烧我的卡路里。。。");
  }

  @GetMapping("/code")
  public HttpResponse<String> getCode(String code) {
    log.info(code);
    return HttpResponse.ok("hello, " + code);
  }

  @GetMapping("/get-session-timeout")
  public int getSessionTimeout(HttpServletRequest request) {
    return request.getSession().getMaxInactiveInterval();
  }

}
