package com.github.smile.ryan.framework.auth.service.impl;

import com.github.smile.ryan.framework.auth.common.util.BeanUtils;
import com.github.smile.ryan.framework.auth.model.entity.AuthResourceEntity;
import com.github.smile.ryan.framework.auth.model.response.AuthResourceResponse;
import com.github.smile.ryan.framework.auth.repository.AuthResourceRepository;
import com.github.smile.ryan.framework.auth.service.AuthResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 名称：AuthResourceServiceImpl
 * 描述：AuthResourceServiceImpl.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Service
public class AuthResourceServiceImpl implements AuthResourceService {

    @Autowired
    private AuthResourceRepository authResourceRepository;

    @Override
    public AuthResourceResponse findOneById(Long resourceId) {
        AuthResourceEntity resourceEntity = authResourceRepository.findOneById(resourceId);
        AuthResourceResponse resourceResponse = new AuthResourceResponse();
        BeanUtils.copyProperties(resourceEntity, resourceResponse);
        return resourceResponse;
    }
}
