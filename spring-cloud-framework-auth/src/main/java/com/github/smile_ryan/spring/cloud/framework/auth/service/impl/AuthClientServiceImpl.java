package com.github.smile_ryan.spring.cloud.framework.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.smile_ryan.spring.cloud.framework.auth.mapper.AuthClientMapper;
import com.github.smile_ryan.spring.cloud.framework.auth.model.entity.AuthClientEntity;
import com.github.smile_ryan.spring.cloud.framework.auth.service.AuthClientService;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 名称：AuthClientServiceImpl
 * 描述：AuthClientServiceImpl.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Service
public class AuthClientServiceImpl extends ServiceImpl<AuthClientMapper, AuthClientEntity> implements AuthClientService {

    @Override
    public AuthClientEntity findClientByClientId(String clientId) {
        return this.baseMapper.findClientByClientId(clientId);
    }
}
