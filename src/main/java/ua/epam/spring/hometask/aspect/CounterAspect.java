package ua.epam.spring.hometask.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.DAO.AspectDAO;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

@Component
@Aspect
public class CounterAspect {

    @Autowired
    AspectDAO aspectDAO;

    @Pointcut("execution(* ua.epam.spring.hometask.DAO.EventDAO.getByName(..))")
    public void allGetByName(){}

    @Before("allGetByName()")
    public void countGetsByName(JoinPoint joinPoint){
        String name = (String) joinPoint.getArgs()[0];
        aspectDAO.saveEventAccessedByName(name);
    }

    @Pointcut("execution(* ua.epam.spring.hometask.domain.Event.getBasePrice(..))")
    public void allGetBasePrice(){}

    @Before("allGetBasePrice()")
    public void countGetBasePrice(JoinPoint joinPoint) throws NoSuchFieldException {
        Event event = (Event) joinPoint.getTarget();
        aspectDAO.saveEventReturningPrice(event.getName());
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.TicketService.save(..))")
    public void allTicketBooking(){}


    @Before("allTicketBooking()")
    public void countTicketSaves(JoinPoint joinPoint){
        Ticket ticket = (Ticket) joinPoint.getArgs()[0];
        Event event = ticket.getEvent();
        aspectDAO.saveTicketsBookedForEvent(event.getName());
    }
}

