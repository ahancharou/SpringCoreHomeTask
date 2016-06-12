package ua.epam.spring.hometask.DAO.impl;

import ua.epam.spring.hometask.DAO.EventDAO;
import ua.epam.spring.hometask.domain.Event;

import java.util.ArrayList;
import java.util.List;

public class EventDAOImpl implements EventDAO{

    public static List<Event> events = new ArrayList<>();

    public static void setEvents(List<Event> events) {
        EventDAOImpl.events = events;
    }

    @Override
    public Event save(Event event) {
        events.add(event);
        return null;
    }

    @Override
    public void remove(Event event) {
        events.remove(event);
    }

    @Override
    public Event getById(Long id) {
        return events.stream().filter(event -> event.getId().equals(id)).findFirst().get();
    }

    @Override
    public Event getByName(String name) {
        return events.stream().filter(event -> event.getName().equals(name)).findFirst().get();
    }

    @Override
    public List<Event> getAll() {
        return events;
    }
}
