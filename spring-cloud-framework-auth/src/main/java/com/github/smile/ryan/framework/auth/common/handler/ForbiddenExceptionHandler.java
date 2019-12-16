package com.github.smile.ryan.framework.auth.common.handler;

import com.github.smile.ryan.framework.auth.common.util.HttpUtils;
import com.github.smile.ryan.framework.auth.model.response.MessageResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

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
public class ForbiddenExceptionHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException ex) throws IOException {
        HttpUtils
            .writerError(MessageResponse.error(HttpStatus.FORBIDDEN.value(), ex.getMessage()), response);
    }
}
