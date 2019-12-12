package com.github.smile.ryan.framework.auth.common.util;

import com.github.smile.ryan.framework.auth.model.domain.AuthUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

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
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            return null;
        }
        if (principal instanceof AuthUserDetails) {
            return (AuthUserDetails) principal;
        }
        return null;
    }

}
