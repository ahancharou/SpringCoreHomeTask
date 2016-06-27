package ua.epam.spring.hometask.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Yuriy_Tkach
 */
public class DomainObject {

    private Long id;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
