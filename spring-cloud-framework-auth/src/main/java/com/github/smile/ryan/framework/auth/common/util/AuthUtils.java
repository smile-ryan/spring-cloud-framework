package com.github.smile.ryan.framework.auth.common.util;

import com.github.smile.ryan.framework.auth.model.domain.AuthUserDetails;
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

}
