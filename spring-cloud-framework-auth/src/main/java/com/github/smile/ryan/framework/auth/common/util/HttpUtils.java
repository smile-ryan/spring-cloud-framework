package com.github.smile.ryan.framework.auth.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.smile.ryan.framework.auth.model.response.HttpResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * 名称：HttpUtils
 * 描述：HttpUtils.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public class HttpUtils {

  // TODO 优化
  public static <T> void writerError(HttpResponse<T> hr, HttpServletResponse response)
      throws IOException {
    response.setContentType("application/json,charset=utf-8");
    response.setStatus(hr.getStatus());
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(response.getOutputStream(), hr);
  }

}
