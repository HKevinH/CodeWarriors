package com.server.codewarriors.repository;

import com.server.codewarriors.model.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
    UserModel findByProviderAndProviderId(String provider, String providerId);
    UserModel findByEmail(String email);
    Optional<UserModel> findByProviderId(String providerId);
}
