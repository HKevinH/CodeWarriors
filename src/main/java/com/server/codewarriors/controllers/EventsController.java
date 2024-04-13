package com.server.codewarriors.controllers;

import com.server.codewarriors.model.EventsModel;
import com.server.codewarriors.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @PostMapping("/addEvents")
    public String addEventsController(@RequestBody EventsModel events)
    {
        try {
            EventsModel newEvents = eventsService.addEvents(events);
            return "Event Added";
        }catch (Exception e) {
            return null;
        }

    }
}
