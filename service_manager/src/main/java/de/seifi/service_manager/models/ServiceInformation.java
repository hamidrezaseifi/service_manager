package de.seifi.service_manager.models;

import de.seifi.service_manager.enums.ServiceStatus;

import java.util.List;

public class ServiceInformation {

    private ServiceModel service;

    private ServiceStatus status;

    private List<ServiceLog> logs;

    public ServiceInformation() {
    }

    public ServiceInformation(ServiceModel service,
                              ServiceStatus status) {
        this.service = service;
        this.status = status;
    }

    public ServiceInformation(ServiceModel service,
                              ServiceStatus status,
                              List<ServiceLog> logs) {
        this.service = service;
        this.status = status;
        this.logs = logs;
    }

    public ServiceModel getService() {
        return service;
    }

    public String getTypeTitle() {
        return service.getServiceName();
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
