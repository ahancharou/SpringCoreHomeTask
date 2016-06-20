package ua.epam.spring.hometask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.epam.spring.hometask.discount.BirthdayDiscount;
import ua.epam.spring.hometask.discount.DiscountStrategy;
import ua.epam.spring.hometask.discount.Every10ticketDiscount;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.service.impl.DiscountServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DiscountConfig {

    @Bean
    public Every10ticketDiscount every10ticketDiscount(){
        return new Every10ticketDiscount();
    }

    @Bean
    public BirthdayDiscount birthdayDiscount(){
        return new BirthdayDiscount();
    }

    @Bean
    public DiscountService discountService (){
        List<DiscountStrategy> strategies = new ArrayList<>();
        strategies.add(every10ticketDiscount());
        strategies.add(birthdayDiscount());

        return new DiscountServiceImpl(strategies);
    }
}
