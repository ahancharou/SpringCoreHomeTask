package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class AuditoriumServiceImpl implements AuditoriumService{

    @Autowired
    private EntityManager em;

    private EntityTransaction t;

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        List<Auditorium> auditoriumList = em.createQuery("SELECT a from Auditorium a").getResultList();
        return new HashSet<>(auditoriumList);
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return (Auditorium) em.createQuery("SELECT a from Auditorium a where a.name =:name").setParameter("name", name).getSingleResult();
    }
}
