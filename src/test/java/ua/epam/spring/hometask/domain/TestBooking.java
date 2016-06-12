package ua.epam.spring.hometask.domain;

import org.junit.Test;
import org.springframework.cglib.core.Local;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ua.epam.spring.hometask.service.AuditoriumService;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.UserService;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBooking {

    private AbstractApplicationContext context =  new FileSystemXmlApplicationContext("src/test/resources/spring.xml");

    private BookingService bookingService = (BookingService) context.getBean("bookingService");

    private AuditoriumService auditoriumService = (AuditoriumService) context.getBean("audithoriumService");

    private EventService eventService = (EventService) context.getBean("eventService");

    private UserService userService = (UserService) context.getBean("userService");

    private boolean setup = setup();

    private LocalDateTime date;
    private String eventName;
    private String email;

    public boolean setup(){
        Auditorium big = (Auditorium) context.getBean("bigAuditorium");
        date = (LocalDateTime) context.getBean("timeDate");

        Event event = (Event) context.getBean("testEvent");
        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        auditoriums.put(date, big);
        event.setAuditoriums(auditoriums);

        NavigableSet<LocalDateTime> airDates = new TreeSet<>();
        airDates.add(date);
        event.setAirDates(airDates);
        eventName = event.getName();
        eventService.save(event);

        User user = (User) context.getBean("customer");
        userService.save(user);
        email = user.getEmail();

        return true;
    }

    @Test
    public void testBooking(){
        LocalDateTime date = (LocalDateTime) context.getBean("timeDate");
        Set<Long> seats = new TreeSet<>();
        seats.add(1L);
        seats.add(2L);
        seats.add(3L);
        seats.add(10L);
        seats.add(30L);

        User user = userService.getUserByEmail(email);
        Event event = eventService.getByName(eventName);

        double price = bookingService.getTicketsPrice(event, date, user, seats);
        System.out.print(price);
        assertEquals(1080.0,price, 0.1);
    }

}
