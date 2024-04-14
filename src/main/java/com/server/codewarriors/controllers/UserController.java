package com.server.codewarriors.controllers;


import com.server.codewarriors.model.EventsModel;
import com.server.codewarriors.model.UserModel;
import com.server.codewarriors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
            if (newUser != null) {
                return newUser;
            }

            return newUser;
        } catch (Exception e) {
            return null;
        }
    }

    // This Function
    @PostMapping("/update/user")
    public UserModel updateUser(@RequestBody UserModel user)
    {
        try {
            System.out.println(user);
            return userService.updateUser(user);
        }catch (Exception e ) {
            return null;
        }
    }

    //
    @GetMapping("/getAllUsers")
    public List<UserModel> getAllUsers() {
        try {
            return userService.getAllUser();
        }
        catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/users/delete/{id}")
    public String deleteUserById(@PathVariable Long id)
    {
        try {
            userService.deleteUserById(id);
            return "User Delete ";
        }
        catch (Exception e) {
            return null;
        }
    }
}
