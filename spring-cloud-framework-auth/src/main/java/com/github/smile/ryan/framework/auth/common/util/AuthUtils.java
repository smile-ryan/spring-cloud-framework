package com.github.smile.ryan.framework.auth.common.util;

import com.github.smile.ryan.framework.auth.model.domain.AuthUserDetails;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * <pre>
 * 名称：AuthUtils
 * 描述：AuthUtils.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public class AuthUtils {

    public static AuthUserDetails getCurrentUser() {
        if (SecurityContextHolder.getContext() != null &&
            SecurityContextHolder.getContext().getAuthentication() != null &&
            SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AuthUserDetails) {
            return (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        throw new OAuth2Exception("Can not get current user info.");
    }


    public static String[] getClientDetails(HttpServletRequest request) {
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

}
