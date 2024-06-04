package com.server.codewarriors.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class ProblemsModel {
    @Id
    private Long id;
    private String title;
    private String description;
    private String difficulty;
    private String solution;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private UserModel userModel;
    private String date;

    @ManyToOne
    @JoinColumn(name = "id_competitive_problems", referencedColumnName = "id")
    private CompetitiveProblemsModel competitiveProblemsModel;
}
