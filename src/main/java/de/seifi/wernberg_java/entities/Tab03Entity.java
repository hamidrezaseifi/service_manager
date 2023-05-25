package de.seifi.wernberg_java.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "Tab_03")
public class Tab03Entity {
    @Id
    private String id;

    private Integer ls;

    private Float bmax;

    public Tab03Entity() {
    }

    public Tab03Entity(String id,
                       Integer ls,
                       Float bmax) {
        this.id = id;
        this.ls = ls;
        this.bmax = bmax;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLs() {
        return ls;
    }

    public void setLs(Integer ls) {
        this.ls = ls;
    }

    public Float getBmax() {
        return bmax;
    }

    public void setBmax(Float bmax) {
        this.bmax = bmax;
    }
}
