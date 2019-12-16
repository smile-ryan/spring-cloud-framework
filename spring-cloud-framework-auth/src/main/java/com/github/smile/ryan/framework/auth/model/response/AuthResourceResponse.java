package com.github.smile.ryan.framework.auth.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 名称：AuthResourceResponse
 * 描述：AuthResourceResponse.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthResourceResponse extends BaseResponse {

    private String resourceCode;

    private String resourceName;

    /**
     * 1:目录, 2:菜单, 3:按钮
     */
    private String resourceType;

    private String resourcePath;

    /**
     * GET, PUT, POST, DELETE
     */
    private String requestMethod;

    private String requestURI;

    private Long parentId;


}
