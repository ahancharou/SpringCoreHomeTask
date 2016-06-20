package ua.epam.spring.hometask.service.impl;

import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.discount.DiscountStrategy;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class DiscountServiceImpl implements DiscountService {

    private List<DiscountStrategy> discounts;

    public DiscountServiceImpl(List<DiscountStrategy> discounts) {
        this.discounts = discounts;
    }

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        byte discount = 0;
        for (DiscountStrategy strategy: discounts) {
            byte currentDiscount = strategy.calculateDiscount(user, airDateTime, numberOfTickets);
            if (currentDiscount > discount) {
                discount = currentDiscount;
            }
        }

        return discount;
    }
}
