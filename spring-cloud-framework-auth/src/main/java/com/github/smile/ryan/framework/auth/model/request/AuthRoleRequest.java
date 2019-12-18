package com.github.smile.ryan.framework.auth.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 名称：AuthRoleResponse
 * 描述：AuthRoleResponse.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthRoleRequest extends BaseRequest {

    private Long userId;

    private Long roleId;

    private String roleCode;

    private String roleName;

    private Long clientId;

}
