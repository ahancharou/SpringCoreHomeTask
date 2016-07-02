package ua.epam.spring.hometask.aspect.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DiscountStatistics")
public class DiscountStatistics {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column
    private String discountName;

    @Column
    private int timesUsed;

    @OneToMany(mappedBy = "discount", cascade = CascadeType.ALL)
    private Set<UserDiscountsStatistics> userDiscountsStatisticsSet = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public int getTimesUsed() {
        return timesUsed;
    }

    public void setTimesUsed(int timesUsed) {
        this.timesUsed = timesUsed;
    }

    public DiscountStatistics() {
    }

    public DiscountStatistics(String discountName, int timesUsed) {
        this.discountName = discountName;
        this.timesUsed = timesUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscountStatistics that = (DiscountStatistics) o;

        if (id != that.id) return false;
        if (timesUsed != that.timesUsed) return false;
        return discountName != null ? discountName.equals(that.discountName) : that.discountName == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (discountName != null ? discountName.hashCode() : 0);
        result = 31 * result + timesUsed;
        return result;
    }

    public Set<UserDiscountsStatistics> getUserDiscountsStatisticsSet() {
        return userDiscountsStatisticsSet;
    }

    public void setUserDiscountsStatisticsSet(Set<UserDiscountsStatistics> userDiscountsStatisticsSet) {
        this.userDiscountsStatisticsSet = userDiscountsStatisticsSet;
    }
}
