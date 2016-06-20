package ua.epam.spring.hometask.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.discount.BirthdayDiscount;
import ua.epam.spring.hometask.discount.Every10ticketDiscount;
import ua.epam.spring.hometask.domain.User;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class DiscountAspect {

    Map<Class, Integer> mapDiscounts = new HashMap<>();
    {
        mapDiscounts.put(BirthdayDiscount.class, 0);
        mapDiscounts.put(Every10ticketDiscount.class, 0);
    }
    Map<Class,Map <User,Integer>> discountToUser = new HashMap<>();
    {
        discountToUser.put(BirthdayDiscount.class, new HashMap<>());
        discountToUser.put(Every10ticketDiscount.class, new HashMap<>());
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.DiscountService.getDiscount(..))")
    public void allDiscounts(){}

    @AfterReturning(value = "allDiscounts()", returning = "retval")
    public void countDiscounts (JoinPoint joinPoint, byte retval){
        User user = (User) joinPoint.getArgs()[0];

        if (retval == 5) {
            mapDiscounts.put(BirthdayDiscount.class, mapDiscounts.get(BirthdayDiscount.class)+1);
            discountToUser.get(BirthdayDiscount.class).put(user, discountToUser.get(BirthdayDiscount.class).get(user)+1);
        } else if (retval > 0){
            mapDiscounts.put(Every10ticketDiscount.class, mapDiscounts.get(Every10ticketDiscount.class)+1);
            discountToUser.get(Every10ticketDiscount.class).put(user, discountToUser.get(Every10ticketDiscount.class).get(user)+1);
        }
    }

    public Map<Class, Integer> getMapDiscounts() {
        return mapDiscounts;
    }

    public Map<Class, Map<User, Integer>> getDiscountToUser() {
        return discountToUser;
    }
}
