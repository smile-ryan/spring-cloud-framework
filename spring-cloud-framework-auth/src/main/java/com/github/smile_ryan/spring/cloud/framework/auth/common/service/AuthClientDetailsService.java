package com.github.smile_ryan.spring.cloud.framework.auth.common.service;

import com.github.smile_ryan.spring.cloud.framework.auth.model.domain.AuthClientDetails;
import com.github.smile_ryan.spring.cloud.framework.auth.model.entity.AuthClientEntity;
import com.github.smile_ryan.spring.cloud.framework.auth.service.AuthClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 名称：AuthClientDetailsService
 * 描述：AuthClientDetailsService.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Service
public final class AuthClientDetailsService implements ClientDetailsService {

    @Autowired
    private AuthClientService clientService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        AuthClientEntity authClientEntity = this.clientService.findClientByClientId(clientId);

        if (authClientEntity == null) {
            throw new ClientRegistrationException("客户端不存在");
        }
        AuthClientDetails details = new AuthClientDetails(authClientEntity);

        return details;
    }

}
