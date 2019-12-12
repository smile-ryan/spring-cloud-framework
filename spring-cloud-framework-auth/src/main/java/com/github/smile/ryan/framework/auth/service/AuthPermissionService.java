package com.github.smile.ryan.framework.auth.service;

import com.github.smile.ryan.framework.auth.model.entity.AuthPermissionEntity;
import java.util.List;

/**
 * <pre>
 * 名称：AuthPermissionService
 * 描述：AuthPermissionService.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public interface AuthPermissionService {

    List<AuthPermissionEntity> findAllByUserId(Long userId);

    List<AuthPermissionEntity> findAllByRoleId(Long roleId);

}
