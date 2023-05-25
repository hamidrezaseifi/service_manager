package de.seifi.wernberg_java.models;

import de.seifi.wernberg_java.enums.ServiceStatus;
import de.seifi.wernberg_java.enums.ServiceType;

import java.util.List;

public class ServiceInformation {

    private ServiceType type;

    private ServiceStatus status;

    private List<ServiceLog> logs;

    public ServiceInformation() {
    }

    public ServiceInformation(ServiceType type,
                              ServiceStatus status) {
        this.type = type;
        this.status = status;
    }

    public ServiceInformation(ServiceType type,
                              ServiceStatus status,
                              List<ServiceLog> logs) {
        this.type = type;
        this.status = status;
        this.logs = logs;
    }

    public ServiceType getType() {
        return type;
    }

    public String getTypeTitle() {
        return type.getTitle();
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public String getStatusTitle() {
        return status.getTitle();
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    public List<ServiceLog> getLogs() {
        return logs;
    }

    public void setLogs(List<ServiceLog> logs) {
        this.logs = logs;
    }

}
