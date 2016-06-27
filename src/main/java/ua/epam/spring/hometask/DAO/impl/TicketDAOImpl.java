package ua.epam.spring.hometask.DAO.impl;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.DAO.TicketDAO;
import ua.epam.spring.hometask.domain.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class TicketDAOImpl implements TicketDAO{

    @Autowired
    private EntityManager em;

    private EntityTransaction t;

    @Override
    public Ticket getById(Long id) {
        return em.find(Ticket.class, id);
    }

    @Override
    public Ticket save(Ticket ticket) {
        t = em.getTransaction();
        t.begin();
        em.persist(ticket);
        t.commit();
        em.clear();
        return ticket;
    }

    @Override
    public void remove(Ticket ticket) {
        t = em.getTransaction();
        t.begin();
        em.remove(ticket);
        t.commit();
        em.clear();
    }

    @Override
    public List<Ticket> getAll() {
        return (List<Ticket>) em.createQuery("SELECT t from Ticket t").getResultList();
    }

    @Override
    public Set<Ticket> getTicketsByEventAndDate(long eventId, LocalDateTime date) {
        List<Ticket> tickets = em.createQuery("SELECT t from Ticket t where t.event=:eventId and t.dateTime=:date").setParameter("eventId", eventId).setParameter("date", date).getResultList();
        return new HashSet<>(tickets);
    }
}
