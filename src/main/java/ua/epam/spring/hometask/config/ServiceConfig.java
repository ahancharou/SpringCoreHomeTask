package ua.epam.spring.hometask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.*;
import ua.epam.spring.hometask.service.impl.*;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ServiceConfig extends AppConfig{

    @Bean
    public AuditoriumService auditoriumService(){
        Set<Auditorium> auditoria = new HashSet<>();
        auditoria.add(bigAuditorium());

        return new AuditoriumServiceImpl(auditoria);
    }

    @Bean
    public BookingService bookingService(){
        return new BookingServiceImpl();
    }

    @Bean
    public EventService eventService(){
        return new EventServiceImpl();
    }

    @Bean
    public TicketService ticketService(){
        return new TicketServiceImpl();
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

}
