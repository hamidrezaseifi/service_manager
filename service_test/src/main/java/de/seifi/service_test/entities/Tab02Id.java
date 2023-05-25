package de.seifi.service_test.entities;

import java.io.Serializable;

public class Tab02Id implements Serializable {
    private String id;

    private Integer ls;

    public Tab02Id() {
    }

    public Tab02Id(String id,
                   Integer ls) {
        this.id = id;
        this.ls = ls;
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

}
