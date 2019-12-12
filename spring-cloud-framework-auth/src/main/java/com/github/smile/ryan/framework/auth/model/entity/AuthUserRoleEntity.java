package com.github.smile.ryan.framework.auth.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 名称：AuthUserRoleEntity
 * 描述：AuthUserRoleEntity.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("auth_user_role")
public class AuthUserRoleEntity extends BaseEntity {

    private Long userId;

    private Long roleId;

}
