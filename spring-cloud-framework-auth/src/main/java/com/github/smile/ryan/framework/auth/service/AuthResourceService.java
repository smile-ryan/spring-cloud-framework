package com.github.smile.ryan.framework.auth.service;

import com.github.smile.ryan.framework.auth.model.response.AuthResourceResponse;

/**
 * <pre>
 * 名称：AuthResourceService
 * 描述：AuthResourceService.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public interface AuthResourceService {

    AuthResourceResponse findOneById(Long resourceId);

}
