package com.github.smile.ryan.framework.auth.service.impl;

import com.github.smile.ryan.framework.auth.model.entity.AuthPermissionEntity;
import com.github.smile.ryan.framework.auth.repository.AuthPermissionRepository;
import com.github.smile.ryan.framework.auth.service.AuthPermissionService;
import java.util.List;
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

    @Override
    public List<AuthPermissionEntity> findAllByUserId(Long userId) {
        return authPermissionRepository.findAllByUserId(userId);
    }

    @Override
    public List<AuthPermissionEntity> findAllByRoleId(Long roleId) {
        return authPermissionRepository.findAllByRoleId(roleId);
    }
}
