package com.github.smile.ryan.framework.auth.common.service;

import com.github.smile.ryan.framework.auth.common.util.BeanUtils;
import com.github.smile.ryan.framework.auth.model.domain.AuthUserDetails;
import com.github.smile.ryan.framework.auth.model.entity.AuthUserEntity;
import com.github.smile.ryan.framework.auth.model.response.AuthRoleResponse;
import com.github.smile.ryan.framework.auth.service.AuthRoleService;
import com.github.smile.ryan.framework.auth.service.AuthUserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 名称：AuthUserDetailService
 * 描述：AuthUserDetailService.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Service
public final class AuthUserDetailService implements UserDetailsService {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private AuthRoleService authRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUserEntity authUserEntity = this.authUserService.findByUserName(username);
        if (authUserEntity == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        AuthUserDetails authUserDetails = new AuthUserDetails();
        BeanUtils.copyProperties(authUserEntity, authUserDetails);
        List<AuthRoleResponse> roles = authRoleService.findAllByUserId(authUserEntity.getId());
        List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleCode())).collect(Collectors.toList());
        authUserDetails.setAuthorities(authorities);
        return authUserDetails;
    }
}
