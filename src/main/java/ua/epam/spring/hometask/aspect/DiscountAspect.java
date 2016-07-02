package ua.epam.spring.hometask.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.DAO.AspectDAO;
import ua.epam.spring.hometask.domain.User;

@Component
@Aspect
public class DiscountAspect {

    @Autowired
    AspectDAO aspectDAO;

    @Pointcut("execution(* ua.epam.spring.hometask.service.DiscountService.getDiscount(..))")
    public void allDiscounts(){}

    @AfterReturning(value = "allDiscounts()", returning = "retval")
    public void countDiscounts (JoinPoint joinPoint, byte retval){
        User user = (User) joinPoint.getArgs()[0];

        if (retval == 5) {
            aspectDAO.saveDiscountUsage("birthdayDiscount", user.getFirstName()+user.getLastName());
        } else if (retval > 0){
            aspectDAO.saveDiscountUsage("Every10ticketDiscount", user.getFirstName()+user.getLastName());
        }
    }
}
