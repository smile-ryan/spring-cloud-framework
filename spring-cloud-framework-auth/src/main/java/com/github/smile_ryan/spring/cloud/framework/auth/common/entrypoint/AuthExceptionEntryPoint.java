package com.github.smile_ryan.spring.cloud.framework.auth.common.entrypoint;

import com.github.smile_ryan.spring.cloud.framework.auth.common.util.HttpUtils;
import com.github.smile_ryan.spring.cloud.framework.auth.model.response.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
public class AuthExceptionEntryPoint extends OAuth2AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        HttpUtils.writerError(HttpResponse.error(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), response);
    }

}
