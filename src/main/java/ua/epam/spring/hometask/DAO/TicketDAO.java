package ua.epam.spring.hometask.DAO;

import ua.epam.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;
import java.util.Set;

public interface TicketDAO extends AbstractDAO<Ticket> {

    public Set<Ticket> getTicketsByEventAndDate(long eventId, LocalDateTime date);
}
