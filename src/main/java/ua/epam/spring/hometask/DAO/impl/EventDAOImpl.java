package ua.epam.spring.hometask.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.DAO.EventDAO;
import ua.epam.spring.hometask.domain.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
@Service
public class EventDAOImpl implements EventDAO{

    @Autowired
    private EntityManager em;

    private EntityTransaction t;

    @Override
    public Event save(Event event) {
        t = em.getTransaction();
        t.begin();
        em.joinTransaction();
        em.persist(event);
        t.commit();
        em.clear();
        return event;
    }

    @Override
    public void remove(Event event) {
        t = em.getTransaction();
        t.begin();
        em.joinTransaction();
        em.remove(event);
        t.commit();
        em.close();
    }

    @Override
    public Event getById(Long id) {
        return em.find(Event.class, id);
    }

    @Override
    public Event getByName(String name) {
        return (Event) em.createQuery("SELECT e from Event e where e.name=:eventName").setParameter("eventName", name).getSingleResult();
    }

    @Override
    public List<Event> getAll() {
        return (List<Event>) em.createQuery("SELECT e from Event e").getResultList();
    }
}
