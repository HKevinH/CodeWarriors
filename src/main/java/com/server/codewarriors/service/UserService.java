package com.server.codewarriors.service;

import com.server.codewarriors.model.UserModel;
import com.server.codewarriors.repository.UserRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.List;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
         if (user == null)
         {
             System.out.println("User not found");
             return null;
         }

        if (user != null) {
            if (PasswordService.validatePassword(password, user.getPassword())) {
                user.setActive(1);  // 1 means user is logged in
                return user;
            }
            else {
                return null;
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


    public UserModel registerOrGetUser(OAuth2User oauth2User, String provider){
        String providerName = oauth2User.getAttribute("sub");
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String providerId = oauth2User.getAttribute("id");

        UserModel user = userRepository.findByProviderAndProviderId(provider, providerId);
        if (user == null) {
            user = new UserModel();
            user.setUsername(name);
            user.setEmail(email);
            user.setProvider(provider);
            user.setProviderId(providerId);
            user.setActive(1);
            user.setRole("USER");
            user = userRepository.save(user);
        }
        return user;
    }

    public UserModel updateUserPassword(Long userId, String password) {
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }
}
