package com.github.smile.ryan.framework.auth.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 名称：AuthResourceEntity
 * 描述：AuthResourceEntity.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("auth_resource")
public class AuthResourceEntity extends BaseEntity {

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
