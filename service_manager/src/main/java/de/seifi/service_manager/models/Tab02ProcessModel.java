package de.seifi.service_manager.models;


import de.seifi.service_manager.entities.Tab02Entity;

public class Tab02ProcessModel {

    private String id;

    private Integer ls;

    private Float move1;

    private Float move2;

    private Float vol1;

    private Float vol2;

    private Float latest;

    private Float calcValue;

    public Tab02ProcessModel(Tab02Entity entity) {
        this.id = entity.getId();
        this.ls = entity.getLs();
        this.move1 = entity.getMove1();
        this.move2 = entity.getMove2();
        this.vol1 = entity.getVol1();
        this.vol2 = entity.getVol2();
        this.latest = entity.getLatest();

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

    public Float getCalcValue() {
        return calcValue;
    }

    public void setCalcValue(Float calcValue) {
        this.calcValue = calcValue;
    }

    public String getIdentityValue() {
        String idVal = String.format("%s:%s:%s:%s:%s:%s:%s", this.id, this.ls, this.move1, this.move2, this.vol1, this.vol2, this.latest);
        return idVal;
    }

    public void checkValues() {
        if(ls == null){
            ls = 0;
        }
        if(move1 == null){
            move1 = 0f;
        }
        if(move2 == null){
            move2 = 0f;
        }
        if(vol1 == null){
            vol1 = 0f;
        }
        if(vol2 == null){
            vol2 = 0f;
        }
        if(latest == null){
            latest = 0f;
        }
    }

    public void calculate() {
        if(vol1 == 0 && vol2 == 0){
            setCalcValue(0f);
        }
        else{
            if(ls == 1){
                setCalcValue(vol1 / vol2);
            }
            else{
                if(ls == 2){
                    setCalcValue(vol2 / vol1);
                }
                else{
                    setCalcValue(0f);
                }
            }

        }
    }
}
