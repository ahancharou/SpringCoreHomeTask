package ua.epam.spring.hometask.discount;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component("birthdayDiscount")
public class BirthdayDiscount implements DiscountStrategy {

    @Override
    public byte calculateDiscount(User user, LocalDateTime airDate, long seats) {
        byte discount = 0;

        LocalDate birthday = user.getBirthday();
        if (birthday.getMonth() == airDate.getMonth()) {
            int diff = birthday.getDayOfMonth() - airDate.getDayOfMonth();
            if (diff < 6 && diff > -6){
               discount = 5;
            }
        }
        return discount;
    }
}
