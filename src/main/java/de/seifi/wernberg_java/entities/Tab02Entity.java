package de.seifi.wernberg_java.entities;

import javax.persistence.*;

@Entity
@Table(name = "Tab_02")
@IdClass(Tab02Id.class)
public class Tab02Entity {
    @Id
    private String id;

    @Id
    private Integer ls;

    private Float move1;

    private Float move2;

    private Float vol1;

    private Float vol2;

    private Float latest;

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

    public Float getMove1() {
        return move1;
    }

    public void setMove1(Float move1) {
        this.move1 = move1;
    }

    public Float getMove2() {
        return move2;
    }

    public void setMove2(Float move2) {
        this.move2 = move2;
    }

    public Float getVol1() {
        return vol1;
    }

    public void setVol1(Float vol1) {
        this.vol1 = vol1;
    }

    public Float getVol2() {
        return vol2;
    }

    public void setVol2(Float vol2) {
        this.vol2 = vol2;
    }

    public Float getLatest() {
        return latest;
    }

    public void setLatest(Float latest) {
        this.latest = latest;
    }
}
