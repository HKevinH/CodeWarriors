package com.server.codewarriors.service;

import com.server.codewarriors.model.EventsModel;
import com.server.codewarriors.repository.EventsRepository;
import org.springframework.stereotype.Service;


@Service
public class EventsService {
    private final EventsRepository eventsRepository;

    public EventsService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }
    public EventsModel addEvents(EventsModel events)
    {
        return eventsRepository.save(events);
    }

}
