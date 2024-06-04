package com.server.codewarriors.controllers;


import com.server.codewarriors.model.UserModel;
import com.server.codewarriors.repository.UserRepository;
import com.server.codewarriors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


//This Controller is used to users services
@RestController
public class UserController {

    @Autowired
    private  final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
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

            UserModel user1 = userService.loginUser(user.getUsername(), user.getPassword());
            System.out.println(user1 + "user1");

            return null;
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

    @PostMapping("/user/update")
    public ResponseEntity<String> updateProfile(@RequestBody UserModel user, @AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        try {
            UserModel userBy = userRepository.findById(user.getId()).orElse(null);
            if (userBy == null) {
                UserModel register = userService.registerOrGetUser(principal, "google");
            } else {
                System.out.println("User: " + userBy.getPassword() + " " + userBy.getEmail() + " " + userBy.getId());
                UserModel updateUser =  userService.updateUserPassword(userBy.getId(), user.getPassword(), user.getEmail(), user.getLastname()).orElse(null);
            }
            return ResponseEntity.ok("OK"   );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user: " + e.getMessage());
        }
    }
}
