package ua.epam.spring.hometask.DAO.impl;

import ua.epam.spring.hometask.DAO.UserDAO;
import ua.epam.spring.hometask.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{

    private static List<User> userStore = new ArrayList<>();

    public List<User> getAll(){
        return userStore;
    }

    public User getById(Long userId){
        return userStore.stream().filter(user -> user.getId().equals(userId)).findFirst().get();
    }

    public User getByEmail(String email){
        return userStore.stream().filter(user -> user.getEmail().equals(email)).findFirst().get();
    }

    public User save(User user){
        userStore.add(user);
        return user;
    }

    public void remove(User user){
        userStore.remove(user);
    }
}
