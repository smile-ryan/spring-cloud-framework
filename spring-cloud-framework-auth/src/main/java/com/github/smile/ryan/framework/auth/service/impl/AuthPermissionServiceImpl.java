package com.github.smile.ryan.framework.auth.service.impl;

import com.github.smile.ryan.framework.auth.common.util.BeanUtils;
import com.github.smile.ryan.framework.auth.model.entity.AuthPermissionEntity;
import com.github.smile.ryan.framework.auth.model.request.AuthPermissionRequest;
import com.github.smile.ryan.framework.auth.model.response.AuthPermissionResponse;
import com.github.smile.ryan.framework.auth.model.response.AuthResourceResponse;
import com.github.smile.ryan.framework.auth.repository.AuthPermissionRepository;
import com.github.smile.ryan.framework.auth.service.AuthPermissionService;
import com.github.smile.ryan.framework.auth.service.AuthResourceService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 名称：AuthPermissionServiceImpl
 * 描述：AuthPermissionServiceImpl.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Service
public class AuthPermissionServiceImpl implements AuthPermissionService {

    @Autowired
    private AuthPermissionRepository authPermissionRepository;

    @Autowired
    private AuthResourceService authResourceService;

    @Override
    public List<AuthPermissionResponse> findAllByUserId(Long userId) {
        List<AuthPermissionEntity> permissionEntities = authPermissionRepository.findAllByUserId(userId);
        return permissionEntities.stream().map(permissionEntity -> {
            AuthPermissionResponse permissionResponse = new AuthPermissionResponse();
            BeanUtils.copyProperties(permissionEntity, permissionResponse);
            return permissionResponse;
        }).collect(Collectors.toList());
    }

    @Override
    public List<AuthPermissionResponse> findAllByRoleId(Long roleId) {
        List<AuthPermissionEntity> permissionEntities = authPermissionRepository.findAllByRoleId(roleId);
        return permissionEntities.stream().map(permissionEntity -> {
            AuthResourceResponse resourceResponse = authResourceService.findOneById(permissionEntity.getResourceId());
            AuthPermissionResponse permissionResponse = new AuthPermissionResponse();
            BeanUtils.copyProperties(permissionEntity, permissionResponse);
            permissionResponse.setResource(resourceResponse);
            return permissionResponse;
        }).collect(Collectors.toList());
    }

    @Override
    public List<AuthPermissionResponse> findPermissionsByParameters(AuthPermissionRequest permissionRequest) {
        List<AuthPermissionEntity> permissionEntities = authPermissionRepository.findPermissionsByParameters(permissionRequest);
        return permissionEntities.stream().map(permissionEntity -> {
            AuthPermissionResponse permissionResponse = new AuthPermissionResponse();
            BeanUtils.copyProperties(permissionEntity, permissionResponse);
            return permissionResponse;
        }).collect(Collectors.toList());
    }
}
