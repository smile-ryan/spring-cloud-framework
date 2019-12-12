package com.github.smile.ryan.framework.auth.web.api;

import com.github.smile.ryan.framework.auth.model.response.HttpResponse;
import java.security.Principal;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * 名称：AuthController
 * 描述：AuthController.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

//  @Autowired
//  private ConsumerTokenServices consumerTokenServices;

    @Autowired
    private DefaultTokenServices defaultTokenServices;

    @Autowired
    private AccessTokenConverter checkTokenAccessTokenConverter;

    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @DeleteMapping("/revoke_token")
    public ResponseEntity revokeToken(String access_token) {
        boolean result = defaultTokenServices.revokeToken(access_token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/hasPermission")
    public HttpResponse<Boolean> hasPermission(HttpServletRequest request) {
        if (StringUtils
            .equalsIgnoreCase(request.getHeader("X-Strict-Strategy"), Boolean.toString(true))) {
            return HttpResponse.error(404, "资源不存在");
        }
        return HttpResponse.ok(true);
    }

    @RequestMapping(value = "/check_token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, ?> checkToken(@RequestParam("token") String value) {
        OAuth2AccessToken token = defaultTokenServices.readAccessToken(value);
        if (token == null) {
            throw new InvalidTokenException("Token was not recognised");
        }
        if (token.isExpired()) {
            throw new InvalidTokenException("Token has expired");
        }
        OAuth2Authentication authentication = defaultTokenServices.loadAuthentication(token.getValue());
        return checkTokenAccessTokenConverter.convertAccessToken(token, authentication);
    }
}
