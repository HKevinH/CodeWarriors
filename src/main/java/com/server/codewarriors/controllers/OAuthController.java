package com.server.codewarriors.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

@RestController
public class OAuthController {
    @GetMapping("/oauth2/authorization/github")
    public void redirectToGitHub(HttpServletResponse response) throws IOException {
        String clientId = "Ov23liokSeMgGMa2zsbe";
        String redirectUri = "http://localhost:8080/login/oauth2/code/github";
        String state = "random_state";
        String url = "https://github.com/login/oauth/authorize?response_type=code&client_id=" + clientId
                + "&scope=read:user&state=" + state
                + "&redirect_uri=" + redirectUri;

        response.sendRedirect(url);
    }
    @GetMapping("/login/oauth2/code/google")
    public void redirectToGoogle(HttpServletResponse response) throws IOException {
        String clientId = "391002710928-kj4fufc6ltnjo3p5bcut5kmvfqe79n9j.apps.googleusercontent.com";
        String redirectUri = "http://localhost:8080/login/oauth2/code/google";
        String state = "random_state";
        String url = "https://accounts.google.com/o/oauth2/auth?response_type=code&client_id=" + clientId
                + "&scope=read:user&state=" + state
                + "&redirect_uri=" + redirectUri;

        response.sendRedirect(url);
    }

    @GetMapping("/user/oauth")
    public Map<String, Object> getUserUserAuth(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
    }
}
