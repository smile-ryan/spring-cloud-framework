package com.github.smile.ryan.framework.auth.web.controller;

import com.github.smile.ryan.framework.auth.model.response.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * 名称：PrivilegeController
 * 描述：PrivilegeController.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

  @PreAuthorize("permitAll()")
  @GetMapping("/permitAll")
  public HttpResponse<String> permitAll() {
    return HttpResponse.ok("permitAll");
  }

}
