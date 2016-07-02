package ua.epam.spring.hometask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.epam.spring.hometask.DAO.AspectDAO;
import ua.epam.spring.hometask.DAO.EventDAO;
import ua.epam.spring.hometask.DAO.TicketDAO;
import ua.epam.spring.hometask.DAO.UserDAO;
import ua.epam.spring.hometask.DAO.impl.EventDAOImpl;
import ua.epam.spring.hometask.DAO.impl.TicketDAOImpl;
import ua.epam.spring.hometask.DAO.impl.UserDAOImpl;

@Configuration
public class DAOConfig {

    @Bean
    public EventDAO eventDAO(){
        return new EventDAOImpl();
    }

    @Bean
    public TicketDAO ticketDAO(){
        return new TicketDAOImpl();
    }

    @Bean
    public UserDAO userDAO(){
        return new UserDAOImpl();
    }

    @Bean
    public AspectDAO aspectDAO (){ return new AspectDAO();}
}
