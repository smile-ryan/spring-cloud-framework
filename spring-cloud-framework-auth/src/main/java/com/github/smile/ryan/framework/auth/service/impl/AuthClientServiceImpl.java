package com.github.smile.ryan.framework.auth.service.impl;

import com.github.smile.ryan.framework.auth.model.entity.AuthClientEntity;
import com.github.smile.ryan.framework.auth.repository.AuthClientRepository;
import com.github.smile.ryan.framework.auth.service.AuthClientService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AuthClientServiceImpl implements AuthClientService {

    @Autowired
    private AuthClientRepository authClientRepository;

    @Override
    public AuthClientEntity findClientByClientId(String clientId) {
        return authClientRepository.findClientByClientId(clientId);
    }
}
