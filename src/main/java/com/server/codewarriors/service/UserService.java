package com.server.codewarriors.service;

import com.server.codewarriors.model.UserModel;
import com.server.codewarriors.repository.UserRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

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

        if (user == null) {
            System.out.println("User not found by username, trying email");
            user = userRepository.findByEmail(username);

            if (user == null) {
                System.out.println("User not found by email either");
                return null;
            }
        }

        if (PasswordService.validatePassword(password, user.getPassword())) {
            user.setActive(1);  // 1 means user is logged in
            user.setDate_last_login(new Date());
            return user;
        } else {
            return null;
        }
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


    @Transactional
    public UserModel registerOrGetUser(OAuth2User oauth2User, String provider){
        if (oauth2User == null) {
            return null;
        }
        String providerName = oauth2User.getAttribute("provider_name");
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String providerId = oauth2User.getAttribute("id").toString();
        logger.info("Attempting to find or create user with email: {}, provider: {}, providerId: {}", email, provider, providerId);

        UserModel user = userRepository.findByProviderAndProviderId(provider, providerId);

        if (user == null || user.getId() == null){
            user = new UserModel();
            user.setUsername(name);
            user.setEmail(email);
            user.setProvider(providerName);
            user.setProviderId(providerId);
            user.setActive(1);
            user.setRole("USER");
            user = userRepository.save(user);
        }
        return user;
    }

    public UserModel updateUserPassword(Long userId, String password, String email, String lastname) throws NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println("User ID: " + userId);
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(email);
        user.setLastname(lastname);
        user.setDate_last_login(new Date());
        user.setPassword(PasswordService.generateStrongPasswordHash(password));
        userRepository.save(user);
        return user;
    }
}
