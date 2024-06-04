package com.server.codewarriors.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class CompetitiveProblemsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String author;
    private String title;
    private String description;
    private String difficulty;
    private String tags;
    private Double solvedPercentage;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
