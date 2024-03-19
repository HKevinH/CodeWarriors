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

    public EventsModel() {
    }

    public EventsModel(String id, String title, String description, String date, String author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.author = author;
    }
}
