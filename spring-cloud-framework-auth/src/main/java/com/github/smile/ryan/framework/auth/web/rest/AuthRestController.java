package com.github.smile.ryan.framework.auth.web.rest;

import com.github.smile.ryan.framework.auth.common.service.AuthTokenService;
import com.github.smile.ryan.framework.auth.common.util.AuthUtils;
import com.github.smile.ryan.framework.auth.model.entity.AuthClientEntity;
import com.github.smile.ryan.framework.auth.model.entity.AuthUserEntity;
import com.github.smile.ryan.framework.auth.model.request.AuthPermissionRequest;
import com.github.smile.ryan.framework.auth.model.request.AuthRoleRequest;
import com.github.smile.ryan.framework.auth.model.response.AuthPermissionResponse;
import com.github.smile.ryan.framework.auth.model.response.AuthRoleResponse;
import com.github.smile.ryan.framework.auth.service.AuthClientService;
import com.github.smile.ryan.framework.auth.service.AuthPermissionService;
import com.github.smile.ryan.framework.auth.service.AuthRoleService;
import com.github.smile.ryan.framework.auth.service.AuthUserService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * 名称：PrivilegeController
 * 描述：PrivilegeController.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthRestController {

    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private AuthRoleService authRoleService;

    @Autowired
    private AuthClientService authClientService;

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private AuthPermissionService authPermissionService;

    @Autowired
    private AccessTokenConverter checkTokenAccessTokenConverter;

    @DeleteMapping("/revoke_token")
    public ResponseEntity revokeToken(String access_token) {
        boolean result = authTokenService.revokeToken(access_token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/check_token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, ?> checkToken(@RequestParam("token") String tokenValue, HttpServletRequest request) {
        String[] clientDetails = AuthUtils.getClientDetails(request);
        if (clientDetails == null) {
            throw new OAuth2Exception("Client details is required!");
        }
        OAuth2AccessToken token = authTokenService.readAccessToken(tokenValue);
        if (token == null) {
            throw new InvalidTokenException("Token was not recognised");
        }
        if (token.isExpired()) {
            throw new InvalidTokenException("Token has expired");
        }
        String clientIdStr = clientDetails[0];
        OAuth2Authentication authentication = authTokenService.loadAuthentication(token.getValue());
        AuthClientEntity clientEntity = authClientService.findClientByClientId(clientIdStr);
        AuthUserEntity userEntity = authUserService.findByUserName(authentication.getName());

        Map<String, Object> map = (Map<String, Object>) checkTokenAccessTokenConverter.convertAccessToken(token, authentication);
        if (AuthClientEntity.PERMISSION_TYPE_1.equals(clientEntity.getPermissionType())) {
            AuthRoleRequest roleRequest = new AuthRoleRequest();
            roleRequest.setClientId(clientEntity.getId());
            roleRequest.setUserId(userEntity.getId());
            List<AuthRoleResponse> roleResponseList = authRoleService.findRolesByParameters(roleRequest);
            Set<String> set = roleResponseList.stream().map(AuthRoleResponse::getRoleCode).collect(Collectors.toSet());
            map.put("authorities", set);
        } else if (AuthClientEntity.PERMISSION_TYPE_2.equals(clientEntity.getPermissionType())) {
            AuthPermissionRequest permissionRequest = new AuthPermissionRequest();
            permissionRequest.setClientId(clientEntity.getId());
            permissionRequest.setUserId(userEntity.getId());
            List<AuthPermissionResponse> permissionResponseList = authPermissionService.findPermissionsByParameters(permissionRequest);
            Set<String> set = permissionResponseList.stream().map(AuthPermissionResponse::getPermissionCode).collect(Collectors.toSet());
            map.put("authorities", set);
        }
        return map;
    }

}
