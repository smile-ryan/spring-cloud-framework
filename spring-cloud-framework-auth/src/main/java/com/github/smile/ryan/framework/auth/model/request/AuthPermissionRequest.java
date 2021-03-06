package com.github.smile.ryan.framework.auth.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 名称：AuthPermissionResponse
 * 描述：AuthPermissionResponse.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthPermissionRequest extends BaseRequest {

    private Long userId;

    private Long roleId;

    private Long permissionId;

    private String permissionCode;

    private String permissionName;

    private Long resourceId;

    private Long operationId;

    private Long clientId;

}
