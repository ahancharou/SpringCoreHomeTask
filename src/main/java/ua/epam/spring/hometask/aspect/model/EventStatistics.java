package ua.epam.spring.hometask.aspect.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EventStatistic")
public class EventStatistics {

    @Column
    private String eventName;

    @Column
    private int accessedByName = 0;

    @Column
    private int returnedPrice = 0;

    @Column
    private int ticketsBooked = 0;

    public EventStatistics() {
    }

    public EventStatistics(String eventName, int accessedByName, int returnedPrice, int ticketsBooked) {
        this.eventName = eventName;
        this.accessedByName = accessedByName;
        this.returnedPrice = returnedPrice;
        this.ticketsBooked = ticketsBooked;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getReturnedPrice() {
        return returnedPrice;
    }

    public void setReturnedPrice(int returnedPrice) {
        this.returnedPrice = returnedPrice;
    }

    public int getTicketsBooked() {
        return ticketsBooked;
    }

    public void setTicketsBooked(int ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    public int getAccessedByName() {
        return accessedByName;
    }

    public void setAccessedByName(int accessedByName) {
        this.accessedByName = accessedByName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventStatistics that = (EventStatistics) o;

        if (accessedByName != that.accessedByName) return false;
        if (returnedPrice != that.returnedPrice) return false;
        if (ticketsBooked != that.ticketsBooked) return false;
        return eventName != null ? eventName.equals(that.eventName) : that.eventName == null;

    }

    @Override
    public int hashCode() {
        int result = eventName != null ? eventName.hashCode() : 0;
        result = 31 * result + accessedByName;
        result = 31 * result + returnedPrice;
        result = 31 * result + ticketsBooked;
        return result;
    }
}
