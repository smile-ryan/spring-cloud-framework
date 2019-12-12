package com.github.smile.ryan.framework.auth.web.controller;

import com.github.smile.ryan.framework.auth.common.properties.AuthSecurityProperties;
import com.github.smile.ryan.framework.auth.common.service.AuthTokenService;
import com.github.smile.ryan.framework.auth.common.util.CaptchaGenerator;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * 名称：LoginController
 * 描述：LoginController.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Slf4j
@Controller
@SessionAttributes("authorizationRequest")
public class AuthController {

    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private AccessTokenConverter checkTokenAccessTokenConverter;


    @Autowired
    private AuthSecurityProperties authSecurityProperties;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginProcessUrl", authSecurityProperties.getLoginProcessUrl());
        model.addAttribute("captcha", authSecurityProperties.getCaptchaParameterName());
        return "/login";
    }

    @GetMapping(value = {"", "/index"})
    public String index() {
        return "/index";
    }

    @GetMapping("/error")
    public String error() {
        return "/error";
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaGenerator.generate(request, response);
    }

    @RequestMapping("/auth/grant")
    public ModelAndView confirmAccess(@SessionAttribute("authorizationRequest") AuthorizationRequest authorizationRequest) {
        ModelAndView view = new ModelAndView();
        view.setViewName("grant");
        view.addObject("clientId", authorizationRequest.getClientId());
        view.addObject("scope", authorizationRequest.getScope());
        view.addObject("authorize", "/auth/authorize");
        return view;
    }

    @ResponseBody
    @DeleteMapping("/auth/revoke_token")
    public ResponseEntity revokeToken(String access_token) {
        boolean result = authTokenService.revokeToken(access_token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ResponseBody
    @RequestMapping(value = "/auth/check_token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, ?> checkToken(@RequestParam("token") String value) {
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
