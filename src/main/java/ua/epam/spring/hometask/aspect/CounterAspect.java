package ua.epam.spring.hometask.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class CounterAspect {

    private Map<String, Integer> eventsAccessedByName = new HashMap<>();
    private Map<Event, Integer> eventsReturningPrice = new HashMap<>();
    private Map<Event, Integer> ticketsBooked = new HashMap<>();


    @Pointcut("execution(* ua.epam.spring.hometask.DAO.EventDAO.getByName(..))")
    public void allGetByName(){}

    @Before("allGetByName()")
    public void countGetsByName(JoinPoint joinPoint){
        String name = (String) joinPoint.getArgs()[0];

        if (!eventsAccessedByName.containsKey(name)) {
            eventsAccessedByName.put(name,0);
        }
        eventsAccessedByName.put(name, eventsAccessedByName.get(name)+1);
    }

    public Map<String, Integer> getEventsAccessedByName() {
        return eventsAccessedByName;
    }

    @Pointcut("execution(* ua.epam.spring.hometask.domain.Event.getBasePrice(..))")
    public void allGetBasePrice(){}

    @Before("allGetBasePrice()")
    public void countGetBasePrice(JoinPoint joinPoint) throws NoSuchFieldException {
        Event event = (Event) joinPoint.getTarget();

        if(!eventsReturningPrice.containsKey(event)){
            eventsReturningPrice.put(event, 0);
        }
        eventsReturningPrice.put(event, eventsReturningPrice.get(event)+1);
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.TicketService.save(..))")
    public void allTicketBooking(){}


    @Before("allTicketBooking()")
    public void countTicketSaves(JoinPoint joinPoint){
        Ticket ticket = (Ticket) joinPoint.getArgs()[0];
        Event event = ticket.getEvent();

        if (!ticketsBooked.containsKey(event)){
            ticketsBooked.put(event, 0);
        }

        ticketsBooked.put(event, ticketsBooked.get(event)+1);
    }
}

