package ua.epam.spring.hometask.domain;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ua.epam.spring.hometask.service.AuditoriumService;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by alex on 12.06.2016.
 */
public class TestAudithoriumServise {

    public AuditoriumService auditoriumService = (AuditoriumService) (new FileSystemXmlApplicationContext("src/test/resources/spring.xml")).getBean("audithoriumService");

    @Test
    public void testAudithoriums() throws Exception{
        Auditorium big = auditoriumService.getByName("Main Hall");
        assertEquals(big.getNumberOfSeats(), 60);
        assertEquals(big.getName(), "Main Hall");
        assertEquals(big.getVipSeats().size(), 10);

        Auditorium small = auditoriumService.getByName("Second Hall");
        assertEquals(small.getNumberOfSeats(), 20);
        assertEquals(small.getName(), "Second Hall");
        assertEquals(small.getVipSeats().size(), 6);

        Auditorium vip = auditoriumService.getByName("VIP hall");
        assertEquals(vip.getNumberOfSeats(), 10);
        assertEquals(vip.getName(), "VIP hall");
        assertEquals(vip.getVipSeats().size(), 10);
    }
}
