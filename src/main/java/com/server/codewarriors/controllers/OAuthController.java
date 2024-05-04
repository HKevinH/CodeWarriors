package com.server.codewarriors.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class OAuthController {
    @GetMapping("/oauth2/authorization/github")
    public void redirectToGitHub(HttpServletResponse response) throws IOException {
        String clientId = "Ov23liokSeMgGMa2zsbe";
        String redirectUri = "http://localhost:8080/login/oauth2/code/github";
        String state = "random_state"; // Genera un estado aleatorio en producción
        String url = "https://github.com/login/oauth/authorize?response_type=code&client_id=" + clientId
                + "&scope=read:user&state=" + state
                + "&redirect_uri=" + redirectUri;

        response.sendRedirect(url);
    }
    @GetMapping("/oauth2/authorization/google")
    public void redirectToGoogle(HttpServletResponse response) throws IOException {
        String clientId = "Ov23liokSeMgGMa2zsbe";
        String redirectUri = "http://localhost:8080/login/oauth2/code/google";
        String state = "random_state"; // Genera un estado aleatorio en producción
        String url = "https://github.com/login/oauth/authorize?response_type=code&client_id=" + clientId
                + "&scope=read:user&state=" + state
                + "&redirect_uri=" + redirectUri;

        response.sendRedirect(url);
    }
}
