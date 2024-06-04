package com.server.codewarriors.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


// @Entity
// This annotation is used to specify that the class is an entity and is mapped to a database table.
@Getter
@Setter
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    private Integer active;
    private String provider;
    private String providerId;
    private String username;
    private String password;
    private String email;
    private String role;
    private String token;
    private String lastname;
    private Date date_last_login;

    public UserModel orElse(Object o) {
        return null;
    }


}

