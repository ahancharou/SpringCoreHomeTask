package ua.epam.spring.hometask.DAO;

import ua.epam.spring.hometask.domain.Event;

import java.util.List;

/**
 * Created by alex on 12.06.2016.
 */
public interface EventDAO extends AbstractDAO<Event>{

    public Event getByName(String name);

}
