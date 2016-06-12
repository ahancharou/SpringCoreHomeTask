package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.service.TicketService;
import ua.epam.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;

public class BookingServiceImpl implements BookingService {

    private DiscountService discountService;
    private UserService userService;
    private TicketService ticketService;

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        double discount = discountService.getDiscount(user,event,dateTime,seats.size()) / 100;

        EventRating rating = event.getRating();
        double multiplier = (rating == EventRating.HIGH ? 1.2 : (rating == EventRating.LOW ? 0.8 : 1));

        double price = event.getBasePrice();

        Auditorium auditorium = event.getAuditoriums().get(dateTime);
        Set<Long> vipSeats = auditorium.getVipSeats();
        long numberOfVipSeats = vipSeats.stream().filter(seats::contains).count();
        long numberOfNormalSeats = seats.size() - numberOfVipSeats;

        return (((numberOfNormalSeats*price) + (numberOfVipSeats*price*2))*multiplier)*(1-discount);
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {

        tickets.forEach(ticket -> {
            User user = ticket.getUser();
            if ((userService.getById(user.getId())) != null ){
                user.getTickets().add(ticket);
                userService.save(user);
            }
            ticketService.save(ticket);
        }
        );
        /*Ticket should contain information about event, air dateTime, seat, and user.
        The user could be registered or not.
        If user is registered, then booking information is stored for that user (in the tickets collection).
         Purchased tickets for particular event should be stored.
        */
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return ticketService.getTicketsByEventAndDate(event.getId(), dateTime);
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
}
