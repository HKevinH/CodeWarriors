package com.server.codewarriors.repository;

import com.server.codewarriors.model.UserModel;
import com.server.codewarriors.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
