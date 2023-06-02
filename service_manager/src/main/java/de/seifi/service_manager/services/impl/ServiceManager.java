package de.seifi.service_manager.services.impl;

import de.seifi.service_manager.models.ServiceInformation;
import de.seifi.service_manager.models.ServiceLog;
import de.seifi.service_manager.models.ServiceModel;
import de.seifi.service_manager.services.IServiceManager;

import java.util.List;

public class ServiceManager implements IServiceManager {

    private final ServiceModel serviceModel;

    public ServiceManager(ServiceModel serviceModel) {
        this.serviceModel = serviceModel;
    }

    @Override
    public ServiceModel getServiceModel() {
        return serviceModel;
    }

    @Override
    public void setInterval(Long interval) {

    }

    @Override
    public ServiceInformation getServiceInfo() {
        return null;
    }

    @Override
    public List<ServiceLog> getServiceLastLogs() {
        return null;
    }

    @Override
    public void stop() {

    }

    @Override
    public void start() {

    }
}
