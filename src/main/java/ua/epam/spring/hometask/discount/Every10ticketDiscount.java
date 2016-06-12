package ua.epam.spring.hometask.discount;

import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;

public class Every10ticketDiscount implements DiscountStrategy{

    @Override
    public byte calculateDiscount(User user, LocalDateTime airDate, long seats) {
        return (byte) (seats / 10);
    }
}
