package com.github.smile.ryan.framework.auth.common.filter;

import com.github.smile.ryan.framework.auth.common.util.AuthUtils;
import com.github.smile.ryan.framework.auth.common.util.HttpUtils;
import com.github.smile.ryan.framework.auth.model.domain.AuthClientDetails;
import com.github.smile.ryan.framework.auth.model.response.MessageResponse;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * <pre>
 * 名称：BasicAuthenticationFilter
 * 描述：BasicAuthenticationFilter.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Component
public class BasicAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        if (!request.getRequestURI().equals("/auth/token") ||
            !request.getParameter("grant_type").equals("password")) {
            filterChain.doFilter(request, response);
            return;
        }
        String[] clientDetails = AuthUtils.getClientDetails(request);
        if (clientDetails == null) {
            MessageResponse<String> bs = MessageResponse.error(HttpStatus.UNAUTHORIZED.value(), "请求中未包含客户端信息");
            HttpUtils.writerError(bs, response);
            return;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            filterChain.doFilter(request, response);
            return;
        }
        AuthClientDetails details = (AuthClientDetails) this.clientDetailsService.loadClientByClientId(clientDetails[0]);
        if (passwordEncoder.matches(clientDetails[1], details.getClientSecret())) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                details.getClientId(), details.getClientSecret(), details.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
        } else {
            throw OAuth2Exception.create(OAuth2Exception.INVALID_CLIENT, "client_id与client_secret不匹配");
        }
        filterChain.doFilter(request, response);
    }

    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }
}
