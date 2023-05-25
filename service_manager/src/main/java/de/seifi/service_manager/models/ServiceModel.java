package de.seifi.service_manager.models;

import de.seifi.service_manager.entities.ServicesEntity;

import java.util.UUID;


public class ServiceModel {
    private UUID id;

    private String serviceName;

    private String address;

    private Integer status;

    private String comments;

    public ServiceModel() {
    }

    public ServiceModel(ServicesEntity entity) {
        this.id = entity.getId();
        this.serviceName = entity.getServiceName();
        this.address = entity.getAddress();
        this.status = entity.getStatus();
        this.comments = entity.getComments();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
