package com.github.smile.ryan.framework.demo1.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * 名称：Demo1Controller
 * 描述：Demo1Controller.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@RestController
public class SecurityController {

  @GetMapping("/test")
  public String test() {
    return "hello, this is demo1-test";
  }

  @PreAuthorize("hasAnyAuthority('ROLE_USER')")
  @GetMapping("/test2")
  public String test2() {
    return "hello, this is demo1-test2";
  }

  @PreAuthorize("hasRole('USER2')")
  @GetMapping("/test3")
  public String test3() {
    return "hello, this is demo1-test3";
  }

  @PreAuthorize("hasAnyAuthority('auth-service:basic-manager:read-only')")
  @GetMapping("/test4")
  public String test4() {
    return "hello, this is demo1-test4";
  }

  @PreAuthorize("hasAnyAuthority('auth-service:basic-manager:user-manager:user-update:execute')")
  @GetMapping("/test5")
  public String test5() {
    return "hello, this is demo1-test5";
  }




  @GetMapping("/auth/code")
  public String code(String code) {
    return "OAuth2 Code is: " + code;
  }

}
