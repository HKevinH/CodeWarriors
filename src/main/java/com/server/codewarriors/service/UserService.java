package com.server.codewarriors.service;

import com.server.codewarriors.model.UserModel;
import com.server.codewarriors.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    public UserModel updateUser(UserModel user)  throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = user.getPassword();
        user.setPassword(PasswordService.generateStrongPasswordHash(password));
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

    public List <UserModel> getAllUser() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        if (userRepository.existsById(id))
        {
            userRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
}
