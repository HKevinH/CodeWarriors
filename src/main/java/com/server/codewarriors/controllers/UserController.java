package com.server.codewarriors.controllers;


import com.server.codewarriors.model.UserModel;
import com.server.codewarriors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//This Controller is used to users services
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel user) {

        try {
            UserModel newUser = userService.createUser(user);
            String success = "User registered successfully";
            return "User registered successfully";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/login")
    public UserModel loginUser(@RequestBody UserModel user) {
        try {
            System.out.println(user + "user");
            UserModel newUser = userService.loginUser(user.getUsername(), user.getPassword());
            return newUser;
        } catch (Exception e) {
            return null;
        }
    }
}
