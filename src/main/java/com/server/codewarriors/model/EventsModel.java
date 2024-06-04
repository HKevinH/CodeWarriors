package com.server.codewarriors.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class EventsModel {
    @Id
    private String id;
    private String title;
    private String description;
    private String date;
    private String author;
    private Long participants;
}
