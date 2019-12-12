package com.github.smile.ryan.framework.demo1.service;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * 名称：PermissionService
 * 描述：PermissionService.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public interface PermissionService {

    Boolean hasPermission(HttpServletRequest request);

}
