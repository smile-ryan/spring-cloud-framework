package com.github.smile.ryan.framework.auth.service;

import com.github.smile.ryan.framework.auth.model.request.AuthRoleRequest;
import com.github.smile.ryan.framework.auth.model.response.AuthRoleResponse;
import java.util.List;

/**
 * <pre>
 * 名称：AuthRoleService
 * 描述：AuthRoleService.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public interface AuthRoleService {

    List<AuthRoleResponse> findAllByUserId(Long userId);

    List<AuthRoleResponse> findRolesByParameters(AuthRoleRequest roleRequest);

}
