package com.github.smile.ryan.framework.auth.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 名称：AuthOrgEntity
 * 描述：AuthOrgEntity.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthOrgEntity extends BaseEntity {

    private String orgCode;

    private String orgName;

}
