package com.github.smile.ryan.framework.auth.model.response;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 名称：AuthUserResponse
 * 描述：AuthUserResponse.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthUserResponse extends BaseResponse {

    private String username;

    private String email;

    private String gender;

    private Long orgId;

    private Long parentId;

    private List<AuthRoleResponse> roles;

}
