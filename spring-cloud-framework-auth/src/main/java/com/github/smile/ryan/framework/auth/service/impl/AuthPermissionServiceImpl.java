package com.github.smile.ryan.framework.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.smile.ryan.framework.auth.mapper.AuthPermissionMapper;
import com.github.smile.ryan.framework.auth.model.entity.AuthPermissionEntity;
import com.github.smile.ryan.framework.auth.service.AuthPermissionService;
import java.util.List;
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
public class AuthPermissionServiceImpl extends ServiceImpl<AuthPermissionMapper, AuthPermissionEntity> implements AuthPermissionService {

    @Override
    public List<AuthPermissionEntity> findAllByUserId(Long userId) {
        return this.baseMapper.findAllByUserId(userId);
    }

    @Override
    public List<AuthPermissionEntity> findAllByRoleId(Long roleId) {
        return this.baseMapper.findAllByRoleId(roleId);
    }
}
