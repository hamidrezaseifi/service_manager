package de.seifi.wernberg_java.services.impl;

import de.seifi.wernberg_java.entities.Tab01Entity;
import de.seifi.wernberg_java.exceptions.InvalidTa01Column;
import de.seifi.wernberg_java.exceptions.InvalidTa01Id;
import de.seifi.wernberg_java.repositories.Tab01Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Tab01DataReader {

    private final Tab01Repository tab01Repository;

    private final Map<String, Tab01Entity> entityList;

    public Tab01DataReader(Tab01Repository tab01Repository) {
        this.tab01Repository = tab01Repository;
        entityList = new HashMap<>();
    }

    public void reloadData(){
        this.entityList.clear();
        Iterable<Tab01Entity> itr = this.tab01Repository.findAll();
        itr.forEach(ent -> {
            this.entityList.put(ent.getId(), ent);
        });

    }

    public String getDateString(String id, String column){
        if(this.entityList.containsKey(id)){
            Tab01Entity entity = this.entityList.get(id);
            switch (column.toLowerCase())
            {
                case "l1": return entity.getL1();
                case "l2": return entity.getL2();
                case "l3": return entity.getL3();
                case "l4": return entity.getL4();
                case "l5": return entity.getL5();
                case "l6": return entity.getL6();
                case "l7": return entity.getL7();
                case "l8": return entity.getL8();
                case "l9": return entity.getL9();
                case "l10": return entity.getL10();
                case "l11": return entity.getL11();
                case "l12": return entity.getL12();
                case "l13": return entity.getL13();
                case "l14": return entity.getL14();
                case "l15": return entity.getL15();
                case "l16": return entity.getL16();
                case "l17": return entity.getL17();
                case "l18": return entity.getL18();
                case "l19": return entity.getL19();
                case "l20": return entity.getL20();
            }
            throw new InvalidTa01Column();
        }
        throw new InvalidTa01Id();
    }

    public Integer getDateInteger(String id, String column){
        String data = this.getDateString(id, column);
        if(data == null || data.trim().isEmpty()){
            return 0;
        }
        data = data.replace(",", ".");
        return Integer.parseInt(data);
    }

    public Float getDateFloat(String id, String column){

        String data = this.getDateString(id, column);
        if(data == null || data.trim().isEmpty()){
            return 0f;
        }
        data = data.replace(",", ".");

        return Float.parseFloat(data);
    }

}
