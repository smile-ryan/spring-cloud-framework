package com.github.smile.ryan.framework.auth.service.impl;

import com.github.smile.ryan.framework.auth.model.entity.AuthRoleEntity;
import com.github.smile.ryan.framework.auth.repository.AuthRoleRepository;
import com.github.smile.ryan.framework.auth.service.AuthRoleService;
import java.util.List;
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

    @Override
    public List<AuthRoleEntity> findAllByUserId(Long userId) {
        return authRoleRepository.findAllByUserId(userId);
    }
}
