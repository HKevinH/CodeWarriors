package com.server.codewarriors.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerRest {
    @GetMapping("/")
    public String getNone() {

    return "HOLE";
    }

}
