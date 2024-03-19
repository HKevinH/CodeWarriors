package com.server.codewarriors.service;

import com.server.codewarriors.model.UserModel;
import com.server.codewarriors.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel createUser(UserModel user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = user.getPassword();
        user.setPassword(PasswordService.generateStrongPasswordHash(password));

        return userRepository.save(user);
    }

    public UserModel loginUser(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
         UserModel user = userRepository.findByUsername(username);
         System.out.println(user + " userRe");
        if (user != null) {
            if (PasswordService.validatePassword(password, user.getPassword())) {
                user.setActive(1);  // 1 means user is logged in
                return user;
            }
        }
        return null;
    }

    public UserModel updateUser(UserModel user) {
        return userRepository.save(user);
    }

    public UserModel updateRole(Long id, String role) {
        UserModel user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setRole(role);
            return userRepository.save(user);
        }
        return null;
    }
}
