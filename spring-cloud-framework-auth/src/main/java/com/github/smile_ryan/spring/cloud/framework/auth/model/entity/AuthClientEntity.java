package com.github.smile_ryan.spring.cloud.framework.auth.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <pre>
 * 名称：AuthClientEntity
 * 描述：AuthClientEntity.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
@TableName("auth_client")
public class AuthClientEntity extends BaseEntity {

    @NotNull
    private String clientId;

    private String resourceIds;

    private Boolean isSecretRequired;

    @NotNull
    private String clientSecret;

    private Boolean isScoped;

    private String scope;

    @NotNull
    private String authorizedGrantTypes;

    @NotNull
    private String registeredRedirectUri;

    private String authorities;

    private Boolean isAutoApprove;

    @NotNull
    private Integer accessTokenValiditySeconds;

    @NotNull
    private Integer refreshTokenValiditySeconds;

}
