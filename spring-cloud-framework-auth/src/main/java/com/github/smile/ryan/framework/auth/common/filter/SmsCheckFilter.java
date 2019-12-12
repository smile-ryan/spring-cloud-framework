package com.github.smile.ryan.framework.auth.common.filter;

import com.github.smile.ryan.framework.auth.common.exception.CaptchaVerificationException;
import com.github.smile.ryan.framework.auth.common.properties.AuthSecurityProperties;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * <pre>
 * 名称：SmsCheckFilter
 * 描述：SmsCheckFilter.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Getter
@Setter
public class SmsCheckFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;


    private AuthSecurityProperties properties;

    private boolean isDebug = false;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public SmsCheckFilter() {

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        if (this.isDebug) {
            logger.debug("--------------> request method " + request.getMethod());
        }

        if (StringUtils.equals("/authentication/mobile", request.getRequestURI())
            && StringUtils.equalsAnyIgnoreCase(request.getMethod(), "post")) {
            try {
                check(new ServletWebRequest(request));

            } catch (CaptchaVerificationException ex) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, ex);
            }
        } else {

            filterChain.doFilter(request, response);
        }
    }

    private void check(ServletWebRequest request) throws CaptchaVerificationException {

    }

}
