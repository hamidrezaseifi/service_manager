package de.seifi.service_test.enums;

import de.seifi.service_test.exceptions.InvalidServiceType;

public enum ServiceType {
    RANKING("Ranking"),
    PROGRESSION("Progression");


    private final String title;

    ServiceType(String title) {this.title = title;}

    public String getTitle() {
        return title;
    }

    public static ServiceType fromName(String name){
        if(name == null){
            return null;
        }

        for (ServiceType type: values()){
            if(type.toString().toLowerCase().equals(name.toLowerCase())){
                return type;
            }
        }

        throw new InvalidServiceType();
    }
}
