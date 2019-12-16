package com.github.smile.ryan.framework.auth.web.rest;

import com.github.smile.ryan.framework.auth.common.service.AuthTokenService;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
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
    private AccessTokenConverter checkTokenAccessTokenConverter;

    @DeleteMapping("/revoke_token")
    public ResponseEntity revokeToken(String access_token) {
        boolean result = authTokenService.revokeToken(access_token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/check_token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, ?> checkToken(@RequestParam("token") String value, @RequestParam("resource_server_id") String resourceServerId) {
        OAuth2AccessToken token = authTokenService.readAccessToken(value);
        if (token == null) {
            throw new InvalidTokenException("Token was not recognised");
        }
        if (token.isExpired()) {
            throw new InvalidTokenException("Token has expired");
        }
        OAuth2Authentication authentication = authTokenService.loadAuthentication(token.getValue());
        return checkTokenAccessTokenConverter.convertAccessToken(token, authentication);
    }

}
