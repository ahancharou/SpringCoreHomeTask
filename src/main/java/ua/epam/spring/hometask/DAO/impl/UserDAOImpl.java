package ua.epam.spring.hometask.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.DAO.UserDAO;
import ua.epam.spring.hometask.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserDAOImpl implements UserDAO{

    @Autowired
    private EntityManager em;

    private EntityTransaction t;

    public List<User> getAll(){
        return (List<User>) em.createQuery("SELECT u from User u").getResultList();
    }

    public User getById(Long userId){
        return em.find(User.class, userId);
    }

    public User getByEmail(String email){
        return (User)em.createQuery("SELECT u from User u WHERE u.email=:email").setParameter("email", email).getSingleResult();
    }

    public User save(User user){
        t = em.getTransaction();
        t.begin();
        em.persist(user);
        t.commit();
        em.clear();
        return user;
    }

    public void remove(User user){
        t = em.getTransaction();
        t.begin();
        em.remove(user);
        t.commit();
        em.clear();
    }
}
