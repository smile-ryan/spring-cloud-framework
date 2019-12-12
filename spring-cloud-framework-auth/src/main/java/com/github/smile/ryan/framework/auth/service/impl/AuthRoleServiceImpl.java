package com.github.smile.ryan.framework.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.smile.ryan.framework.auth.mapper.AuthRoleMapper;
import com.github.smile.ryan.framework.auth.model.entity.AuthRoleEntity;
import com.github.smile.ryan.framework.auth.service.AuthRoleService;
import java.util.List;
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
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRoleEntity> implements AuthRoleService {

    @Override
    public List<AuthRoleEntity> findAllByUserId(Long userId) {
        return this.baseMapper.findAllByUserId(userId);
    }
}
