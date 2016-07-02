package ua.epam.spring.hometask.aspect.model;

import javax.persistence.*;

@Entity
@Table(name = "UserDiscountsGenerator")
public class UserDiscountsStatistics {

    @Column
    private String userName;

    @ManyToOne
    @JoinColumn(name = "discountId")
    private DiscountStatistics discount;

    @Column
    private int timesUsed;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public DiscountStatistics getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountStatistics discount) {
        this.discount = discount;
    }

    public int getTimesUsed() {
        return timesUsed;
    }

    public void setTimesUsed(int timesUsed) {
        this.timesUsed = timesUsed;
    }

    public UserDiscountsStatistics() {
    }

    public UserDiscountsStatistics(String userName, DiscountStatistics discount, int timesUsed) {
        this.userName = userName;
        this.discount = discount;
        this.timesUsed = timesUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDiscountsStatistics that = (UserDiscountsStatistics) o;

        if (timesUsed != that.timesUsed) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        return discount != null ? discount.equals(that.discount) : that.discount == null;

    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + timesUsed;
        return result;
    }
}
