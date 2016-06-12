package ua.epam.spring.hometask.discount;

import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;

public interface DiscountStrategy {

    public byte calculateDiscount(User user, LocalDateTime airDate, long seats);

    //    Define DiscountService with all strategies as separate beans in separate XML configuration
}
