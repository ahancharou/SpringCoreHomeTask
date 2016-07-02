package ua.epam.spring.hometask.domain;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.spring.hometask.DAO.AspectDAO;
import ua.epam.spring.hometask.aspect.CounterAspect;
import ua.epam.spring.hometask.aspect.DiscountAspect;
import ua.epam.spring.hometask.config.AppConfig;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.UserService;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class TestJavaConfig {

    private ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    private BookingService bookingService = (BookingService) context.getBean("bookingService");

    private EventService eventService = (EventService) context.getBean("eventService");

    private UserService userService = (UserService) context.getBean("userService");

    private String eventName;
    private String email;

    private AspectDAO aspectDAO = (AspectDAO) context.getBean("aspectDAO");
    private boolean setup = setup();

    public boolean setup(){
        Event event = (Event) context.getBean("testEvent");
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

        System.out.print(aspectDAO.getEventsAccessedByName(eventName));
    }

}
