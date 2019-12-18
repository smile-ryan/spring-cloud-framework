package com.github.smile.ryan.framework.auth.service.impl;

import com.github.smile.ryan.framework.auth.model.entity.AuthUserEntity;
import com.github.smile.ryan.framework.auth.repository.AuthUserRepository;
import com.github.smile.ryan.framework.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Override
    public AuthUserEntity findByUserName(String userName) {
        return authUserRepository.findByUserName(userName);
    }

}
