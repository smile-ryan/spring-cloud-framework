package com.github.smile.ryan.framework.demo1.service.impl;

import com.github.smile.ryan.framework.demo1.service.PermissionService;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 名称：PermissionServiceImpl
 * 描述：PermissionServiceImpl.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Override
    public Boolean hasPermission(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String strictStrategy = request.getHeader("X-Strict-Strategy");
        boolean hasPermission = !StringUtils.equalsIgnoreCase(request.getHeader("X-Strict-Strategy"), Boolean.toString(true));

        System.out.println("------------------" + authorization);
        System.out.println("------------------" + strictStrategy);
        System.out.println("------------------" + hasPermission);

        return hasPermission;
    }
}
