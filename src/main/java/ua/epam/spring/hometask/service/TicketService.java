package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;
import java.util.Set;

public interface TicketService extends AbstractDomainObjectService<Ticket>{

    public Set<Ticket> getTicketsByEventAndDate(long eventId, LocalDateTime date);
}
