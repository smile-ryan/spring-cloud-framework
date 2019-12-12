package com.github.smile.ryan.framework.auth.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 名称：AuthUserEntity
 * 描述：AuthUserEntity.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthUserEntity extends BaseEntity {

    private String username;

    private String email;

    private Boolean isEnabled;

    private Boolean isExpired;

    private Boolean isLocked;

    private String password;

    private String gender;

    private Long orgId;

    private Long parentId;

}
