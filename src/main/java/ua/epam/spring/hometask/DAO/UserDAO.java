package ua.epam.spring.hometask.DAO;

import ua.epam.spring.hometask.domain.User;

public interface UserDAO extends AbstractDAO<User>{

    public User getByEmail(String email);
}
