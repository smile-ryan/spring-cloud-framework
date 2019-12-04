package com.github.smile.ryan.framework.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.smile.ryan.framework.auth.model.entity.AuthUserEntity;

/**
 * <pre>
 * 名称：AuthUserService
 * 描述：AuthUserService.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public interface AuthUserService extends IService<AuthUserEntity> {

  AuthUserEntity findByUserName(String userName);

}
