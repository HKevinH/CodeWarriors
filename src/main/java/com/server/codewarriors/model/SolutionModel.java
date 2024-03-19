package com.server.codewarriors.model;


import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Time;


@Getter
@Entity
public class SolutionModel {
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", referencedColumnName = "id")
    private ProblemsModel problemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel idUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private EventsModel idEvent;

    private String sourceCode;
    private String language;
    private String date;
    private String status;
    private Time time;
}
