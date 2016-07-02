package ua.epam.spring.hometask.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.aspect.model.DiscountStatistics;
import ua.epam.spring.hometask.aspect.model.EventStatistics;
import ua.epam.spring.hometask.aspect.model.UserDiscountsStatistics;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

@Service
public class AspectDAO {

    @Autowired
    private EntityManager em;

    private EntityTransaction t;


    public void saveDiscountUsage(String discount, String userName) {
        t = em.getTransaction();
        t.begin();
        em.joinTransaction();
        DiscountStatistics statistics = (DiscountStatistics) em.createQuery("select d from DiscountStatistics d where d.discountName=:discount").setParameter("discount",discount).getSingleResult();
        if (statistics!=null){
            statistics.setTimesUsed(statistics.getTimesUsed()+1);
        } else {
            statistics = new DiscountStatistics(discount, 1);
        }

        Query q  = em.createQuery("select u from UserDiscountsStatistics u where u.discountId=:discount and u.userName=:username").setParameter("discount", statistics.getId());
        q.setParameter("username", userName);
        UserDiscountsStatistics userDiscount = (UserDiscountsStatistics) q.getSingleResult();
        if (userDiscount!= null){
            userDiscount.setTimesUsed(userDiscount.getTimesUsed()+1);
        } else {
            userDiscount = new UserDiscountsStatistics(userName, statistics, 1);
            userDiscount.setDiscount(statistics);
            userDiscount.setUserName(userName);
            userDiscount.setTimesUsed(1);
        }

        statistics.getUserDiscountsStatisticsSet().add(userDiscount);

        em.persist(statistics);
        t.commit();
        em.clear();
    }

    public void saveEventAccessedByName(String name) {
        t = em.getTransaction();
        t.begin();
        em.joinTransaction();

        EventStatistics eventStatistics = (EventStatistics) em.createQuery("select e from EventStatistics e where e.eventName=:event").setParameter("event", name).getSingleResult();

        if (eventStatistics!= null){
            eventStatistics.setAccessedByName(eventStatistics.getAccessedByName()+1);
        } else {
            eventStatistics = new EventStatistics(name, 1, 0, 0);
        }

        em.persist(eventStatistics);

        t.commit();
        em.clear();
    }

    public void saveEventReturningPrice(String name) {
        t = em.getTransaction();
        t.begin();
        em.joinTransaction();

        EventStatistics eventStatistics = (EventStatistics) em.createQuery("select e from EventStatistics e where e.eventName=:event").setParameter("event", name).getSingleResult();

        if (eventStatistics!= null){
            eventStatistics.setReturnedPrice(eventStatistics.getReturnedPrice()+1);
        } else {
            eventStatistics = new EventStatistics(name, 0, 1, 0);
        }

        em.persist(eventStatistics);

        t.commit();
        em.clear();
    }

    public void saveTicketsBookedForEvent(String name) {
        t = em.getTransaction();
        t.begin();
        em.joinTransaction();

        EventStatistics eventStatistics = (EventStatistics) em.createQuery("select e from EventStatistics e where e.eventName=:event").setParameter("event", name).getSingleResult();

        if (eventStatistics!= null){
            eventStatistics.setTicketsBooked(eventStatistics.getTicketsBooked()+1);
        } else {
            eventStatistics = new EventStatistics(name, 0, 0, 1);
        }

        em.persist(eventStatistics);

        t.commit();
        em.clear();
    }

    public int getEventsAccessedByName(String name) {
        t = em.getTransaction();
        t.begin();
        em.joinTransaction();

        EventStatistics eventStatistics = (EventStatistics) em.createQuery("select e from EventStatistics e where e.eventName=:event").setParameter("event", name).getSingleResult();
        if (eventStatistics!= null){
            return eventStatistics.getAccessedByName();
        } else {
            return 0;
        }
    }
}
