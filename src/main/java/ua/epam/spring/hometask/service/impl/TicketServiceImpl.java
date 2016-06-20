package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.DAO.TicketDAO;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.service.TicketService;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Override
    public Set<Ticket> getTicketsByEventAndDate(long eventId, LocalDateTime date) {
        return ticketDAO.getTicketsByEventAndDate(eventId, date);
    }

    @Override
    public Ticket save(@Nonnull Ticket object) {
        return ticketDAO.save(object);
    }

    @Override
    public void remove(@Nonnull Ticket object) {
        ticketDAO.remove(object);
    }

    @Override
    public Ticket getById(@Nonnull Long id) {
        return ticketDAO.getById(id);
    }

    @Nonnull
    @Override
    public Collection<Ticket> getAll() {
        return ticketDAO.getAll();
    }

    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }
}
