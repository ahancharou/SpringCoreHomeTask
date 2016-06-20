package ua.epam.spring.hometask.service.impl;

import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;
@Service
public class AuditoriumServiceImpl implements AuditoriumService{

    private static Set<Auditorium> auditoriums;

    public AuditoriumServiceImpl (Set<Auditorium> auditoriums){
        setAuditoriums(auditoriums);
    }

    public static Set<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public static void setAuditoriums(Set<Auditorium> auditoriums) {
        AuditoriumServiceImpl.auditoriums = auditoriums;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriums;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriums.stream().filter(auditorium -> auditorium.getName().equals(name)).findFirst().get();
    }
}
