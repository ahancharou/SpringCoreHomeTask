package ua.epam.spring.hometask.DAO.impl;

import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.DAO.TicketDAO;
import ua.epam.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class TicketDAOImpl implements TicketDAO{

    private static List<Ticket> tickets = new ArrayList<>();

    @Override
    public Ticket getById(Long id) {
        return tickets.stream().filter(ticket -> ticket.getId().equals(id)).findFirst().get();
    }

    @Override
    public Ticket save(Ticket ticket) {
        tickets.add(ticket);
        return ticket;
    }

    @Override
    public void remove(Ticket ticket) {
        tickets.remove(ticket);
    }

    @Override
    public List<Ticket> getAll() {
        return tickets;
    }

    @Override
    public Set<Ticket> getTicketsByEventAndDate(long eventId, LocalDateTime date) {
        return tickets.stream().filter(ticket -> (ticket.getEvent().getId().equals(eventId))&&(ticket.getDateTime().equals(date))).collect(Collectors.toSet());
    }
}
