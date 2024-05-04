package com.server.codewarriors.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


// @Entity
// This annotation is used to specify that the class is an entity and is mapped to a database table.
@Getter
@Setter
@Entity
public class UserModel {
    private String provider;
    private String providerId;
    private String username;
    @Setter
    private String password;
    @Setter
    private String email;
    private String role;
    private String token;
    private String lastname;
    @Id
    private String id;
    private Integer active;

    public UserModel() {
    }

    public UserModel(String username, String password, String email, String role, String token, String id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.token = token;
        this.id = id;
    }

    public UserModel orElse(Object o) {
        return null;
    }


}

