package com.github.smile.ryan.framework.auth.web.controller;

import com.github.smile.ryan.framework.auth.common.properties.AuthSecurityProperties;
import com.github.smile.ryan.framework.auth.common.util.CaptchaGenerator;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class LoginController {

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


}
