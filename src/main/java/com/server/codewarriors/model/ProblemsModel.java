package com.server.codewarriors.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class ProblemsModel {
    @Id
    private String id;
    private String title;
    private String description;
    private String difficulty;
    private String solution;
    private String author;
    private String date;

    public ProblemsModel() {
    }

    public ProblemsModel(String id, String title, String description, String difficulty, String solution, String author, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.solution = solution;
        this.author = author;
        this.date = date;
    }
}
