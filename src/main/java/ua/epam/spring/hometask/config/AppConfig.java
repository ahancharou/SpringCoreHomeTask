package ua.epam.spring.hometask.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import ua.epam.spring.hometask.aspect.CounterAspect;
import ua.epam.spring.hometask.aspect.DiscountAspect;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

@Configuration
@EnableAspectJAutoProxy
@Import({DiscountConfig.class, DAOConfig.class, ServiceConfig.class,TestPropertyConfig.class})
public class AppConfig {
    private @Value("${name}") String name;
    private @Value("${seats}") int seats;
    private @Value("${vipSeats}") String vip;


    private LocalDateTime now = LocalDateTime.now();

    @Bean
    public User customer(){
        User customer = new User();
        customer.setId(777L);
        customer.setFirstName("Kevin");
        customer.setLastName("Spacey");
        customer.setEmail("kevin@gmail.com");
        customer.setBirthday(LocalDate.of(1959,7,26));

        return customer;
    }

    @Bean
    public Event testEvent(){
        Event event = new Event();
        event.setName("test event");
        event.setId(1L);
        event.setRating(EventRating.HIGH);
        event.setBasePrice(100);
        NavigableSet<LocalDateTime> airDates = new TreeSet<>();
        airDates.add(now);
        event.setAirDates(airDates);
        NavigableMap<LocalDateTime,Auditorium> auditoria = new TreeMap<>();
        auditoria.put(now,bigAuditorium());
        event.setAuditoriums(auditoria);

        return event;
    }

    @Bean
    public Auditorium bigAuditorium(){
        return new Auditorium(name, seats, vip);
    }

    @Bean
    public LocalDateTime timeDate(){
        return now;
    }

    @Bean
    public CounterAspect counterAspect(){return new CounterAspect();}

    @Bean
    public DiscountAspect discountAspect() {return new DiscountAspect();}

    @Bean
    public EntityManager em(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SpringCinema");
        return factory.createEntityManager();
    }
}

