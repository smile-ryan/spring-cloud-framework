package com.github.smile.ryan.framework.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.smile.ryan.framework.auth.mapper.AuthUserMapper;
import com.github.smile.ryan.framework.auth.model.entity.AuthUserEntity;
import com.github.smile.ryan.framework.auth.service.AuthUserService;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 名称：AuthUserServiceImpl
 * 描述：AuthUserServiceImpl.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Service
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUserEntity> implements AuthUserService {

    @Override
    public AuthUserEntity findByUserName(String userName) {
        return this.baseMapper.findByUserName(userName);
    }


}
