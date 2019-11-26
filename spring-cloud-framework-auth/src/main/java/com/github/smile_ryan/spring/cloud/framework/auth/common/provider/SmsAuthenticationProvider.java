package com.github.smile_ryan.spring.cloud.framework.auth.common.provider;

import com.github.smile_ryan.spring.cloud.framework.auth.common.token.SmsAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <pre>
 * 名称：SmsAuthenticationProvider
 * 描述：SmsAuthenticationProvider.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken) authentication;

        UserDetails user = this.userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());

        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        SmsAuthenticationToken authenticationResult = new SmsAuthenticationToken(user, user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getCredentials());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
