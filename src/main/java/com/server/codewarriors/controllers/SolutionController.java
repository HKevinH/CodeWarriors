package com.server.codewarriors.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class SolutionController {

    @PostMapping("/add/solution")
    public String addSolution() {
        return "Solution added successfully";
    }

    @GetMapping("/get/solution")
    public String getSolution() {
        return "Solution retrieved successfully";
    }
}
