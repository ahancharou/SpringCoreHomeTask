package ua.epam.spring.hometask.DAO;

import ua.epam.spring.hometask.domain.DomainObject;

import java.util.Collection;

public interface AbstractDAO<T extends DomainObject> {

    public T getById(Long id);

    public T save(T t);

    public void remove(T t);

    public Collection<T> getAll();

}
