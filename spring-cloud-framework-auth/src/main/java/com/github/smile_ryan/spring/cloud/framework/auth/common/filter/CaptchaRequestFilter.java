package com.github.smile_ryan.spring.cloud.framework.auth.common.filter;

import com.github.smile_ryan.spring.cloud.framework.auth.common.properties.AuthSecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * 名称：CaptchaRequestFilter
 * 描述：CaptchaRequestFilter.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Component
public class CaptchaRequestFilter extends OncePerRequestFilter {


    private AntPathMatcher pathMatcher = new AntPathMatcher();
    private AuthSecurityProperties properties;

    public CaptchaRequestFilter(AuthSecurityProperties properties) {
        this.properties = properties;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //本次请求url
        String path = request.getRequestURI();


        if (pathMatcher.match(properties.getLoginProcessUrl(), path)) {

            // 图片验证码值
            String pCode = request.getParameter(properties.getCaptchaParameterName());


            if (StringUtils.isBlank(pCode)) {
                response.sendRedirect(properties.getLoginPage() + "?error=code is blank");
                return;
            }

            String pRealCode = (String) request.getSession().getAttribute(properties.getCaptchaParameterName());

            if (pRealCode == null) {
                response.sendRedirect(properties.getLoginPage() + "?error=code is expire");
                return;
            }

            if (!StringUtils.equalsIgnoreCase(pCode, pRealCode)) {
                response.sendRedirect(properties.getLoginPage() + "?error=code is error");
                return;
            }

        }


        filterChain.doFilter(request, response);

    }


}
