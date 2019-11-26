package com.github.smile_ryan.spring.cloud.framework.auth.common.handler;

import com.github.smile_ryan.spring.cloud.framework.auth.common.util.HttpUtils;
import com.github.smile_ryan.spring.cloud.framework.auth.model.response.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * 名称：AuthAccessDeniedHandler
 * 描述：AuthAccessDeniedHandler.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Component
public class AuthAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException, ServletException {
        HttpUtils.writerError(HttpResponse.error(HttpStatus.FORBIDDEN.value(), "没有权限"), response);
    }
}
