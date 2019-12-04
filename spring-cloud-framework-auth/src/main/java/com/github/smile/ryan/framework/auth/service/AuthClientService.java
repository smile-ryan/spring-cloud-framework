package com.github.smile.ryan.framework.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.smile.ryan.framework.auth.model.entity.AuthClientEntity;
import org.apache.ibatis.annotations.Param;

/**
 * <pre>
 * 名称：AuthClientService
 * 描述：AuthClientService.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public interface AuthClientService extends IService<AuthClientEntity> {

  AuthClientEntity findClientByClientId(@Param("id") String clientId);

}
