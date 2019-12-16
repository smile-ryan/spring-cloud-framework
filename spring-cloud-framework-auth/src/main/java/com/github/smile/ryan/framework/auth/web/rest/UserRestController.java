package com.github.smile.ryan.framework.auth.web.rest;

import com.github.smile.ryan.framework.auth.common.util.AuthUtils;
import com.github.smile.ryan.framework.auth.common.util.BeanUtils;
import com.github.smile.ryan.framework.auth.model.domain.AuthUserDetails;
import com.github.smile.ryan.framework.auth.model.entity.AuthUserEntity;
import com.github.smile.ryan.framework.auth.model.response.AuthRoleResponse;
import com.github.smile.ryan.framework.auth.model.response.AuthUserResponse;
import com.github.smile.ryan.framework.auth.service.AuthRoleService;
import com.github.smile.ryan.framework.auth.service.AuthUserService;
import com.google.common.collect.Lists;
import java.security.Principal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * 名称：PrivilegeController
 * 描述：PrivilegeController.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private AuthRoleService authRoleService;

    @GetMapping("/info")
    @PreAuthorize("#oauth2.hasScope('user-info')")
    public AuthUserResponse userInfo() {
        AuthUserDetails userDetails = AuthUtils.getCurrentUser();
        AuthUserEntity userEntity = authUserService.findByUserName(userDetails.getUsername());
        AuthUserResponse userResponse = new AuthUserResponse();
        BeanUtils.copyProperties(userEntity, userResponse);
        return userResponse;
    }

    @GetMapping("/detail")
    @PreAuthorize("#oauth2.hasScope('user-detail')")
    public AuthUserResponse userDetails() {
        AuthUserDetails userDetails = AuthUtils.getCurrentUser();
        AuthUserResponse userResponse = new AuthUserResponse();
        AuthUserEntity userEntity = authUserService.findByUserName(userDetails.getUsername());
        List<AuthRoleResponse> roleEntityList = authRoleService.findAllByUserId(userEntity.getId());
        BeanUtils.copyProperties(userEntity, userResponse);
        userResponse.setRoles(roleEntityList);
        return userResponse;
    }

    @GetMapping("/principal")
    @PreAuthorize("#oauth2.hasScope('user-detail')")
    public Principal userPrincipal(Principal user) {
        return user;
    }

    @GetMapping("/list")
    public List<AuthUserEntity> userList() {
        AuthUserDetails authUserDetails = AuthUtils.getCurrentUser();
        AuthUserEntity userEntity = authUserService.findByUserName(authUserDetails.getUsername());
        return Lists.newArrayList(userEntity);
    }

}
