package de.seifi.service_manager.enums;

public enum ServiceStatus {
    NONE("No Status"),
    IDLE("Idle"),
    RUNNUNG("Running"),
    STOPPED("Stopped"),
    ERROR("Error");

    private final String title;

    ServiceStatus(String title) {this.title = title;}

    public String getTitle() {
        return title;
    }
}
