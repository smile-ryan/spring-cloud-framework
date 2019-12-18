package com.github.smile.ryan.framework.auth.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
public class AuthClientEntity extends BaseEntity {

    public static final String PERMISSION_TYPE_1 = "Role-Based-Access-Control";
    public static final String PERMISSION_TYPE_2 = "Resource-Based-Access-Control";

    private String clientId;

    private String resourceIds;

    private Boolean isSecretRequired;

    private String clientSecret;

    private Boolean isScoped;

    private String scope;

    private String authorizedGrantTypes;

    private String registeredRedirectUri;

    private String authorities;

    private Boolean isAutoApprove;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private String permissionType;

}


