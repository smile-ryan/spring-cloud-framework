package com.github.smile.ryan.framework.auth.service.impl;

import com.github.smile.ryan.framework.auth.common.util.BeanUtils;
import com.github.smile.ryan.framework.auth.model.entity.AuthRoleEntity;
import com.github.smile.ryan.framework.auth.model.response.AuthPermissionResponse;
import com.github.smile.ryan.framework.auth.model.response.AuthRoleResponse;
import com.github.smile.ryan.framework.auth.repository.AuthRoleRepository;
import com.github.smile.ryan.framework.auth.service.AuthRoleService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 名称：AuthRoleServiceImpl
 * 描述：AuthRoleServiceImpl.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Service
public class AuthRoleServiceImpl implements AuthRoleService {

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Autowired
    private AuthPermissionServiceImpl authPermissionService;

    @Override
    public List<AuthRoleResponse> findAllByUserId(Long userId) {
        List<AuthRoleEntity> roleEntities = authRoleRepository.findAllByUserId(userId);
        return roleEntities.stream().map(roleEntity -> {
            List<AuthPermissionResponse> permissionResponses = authPermissionService.findAllByRoleId(roleEntity.getId());
            AuthRoleResponse roleResponse = new AuthRoleResponse();
            BeanUtils.copyProperties(roleEntity, roleResponse);
            roleResponse.setPermissions(permissionResponses);
            return roleResponse;
        }).collect(Collectors.toList());
    }
}
