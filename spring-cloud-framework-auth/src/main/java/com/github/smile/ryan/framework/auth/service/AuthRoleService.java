package com.github.smile.ryan.framework.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.smile.ryan.framework.auth.model.entity.AuthRoleEntity;
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
public interface AuthRoleService extends IService<AuthRoleEntity> {

    List<AuthRoleEntity> findAllByUserId(Long userId);

}
