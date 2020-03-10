package com.uthanks.services;

import com.uthanks.domain.Event;
import com.uthanks.domain.User;
import com.uthanks.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public void save(Event event, User user) {
        event.setCreator(user);
        user.addEvent(event);
        eventRepository.save(event);
    }
}
