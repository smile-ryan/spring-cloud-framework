package com.github.smile_ryan.spring.cloud.framework.auth.common.filter;

import com.github.smile_ryan.spring.cloud.framework.auth.common.util.HttpUtils;
import com.github.smile_ryan.spring.cloud.framework.auth.model.domain.AuthClientDetails;
import com.github.smile_ryan.spring.cloud.framework.auth.model.response.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getRequestURI().equals("/auth/token") ||
                !request.getParameter("grant_type").equals("password")) {
            filterChain.doFilter(request, response);
            return;
        }
        String[] clientDetails = this.isHasClientDetails(request);
        if (clientDetails == null) {
            HttpResponse bs = HttpResponse.error(HttpStatus.UNAUTHORIZED.value(), "请求中未包含客户端信息");
            HttpUtils.writerError(bs, response);
            return;
        }
        this.handle(request, response, clientDetails, filterChain);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, String[] clientDetails, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            filterChain.doFilter(request, response);
            return;
        }
        AuthClientDetails details = (AuthClientDetails) this.clientDetailsService.loadClientByClientId(clientDetails[0]);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(details.getClientId(), details.getClientSecret(), details.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request, response);
    }

    private String[] isHasClientDetails(HttpServletRequest request) {
        String[] params = null;
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null) {
            String basic = authorizationHeader.substring(0, 5);
            if (basic.toLowerCase().contains("basic")) {
                String tmp = authorizationHeader.substring(6);
                String defaultClientDetails = new String(Base64.getDecoder().decode(tmp));
                String[] clientArrays = defaultClientDetails.split(":");
                if (clientArrays.length == 2) {
                    return clientArrays;
                }
            }
            return null;
        }
        String id = request.getParameter("client_id");
        String secret = request.getParameter("client_secret");
        if (id != null && secret != null) {
            params = new String[]{id, secret};
        }
        return params;
    }

    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }
}
