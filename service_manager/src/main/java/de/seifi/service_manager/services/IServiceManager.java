package de.seifi.service_manager.services;

import de.seifi.service_manager.models.ServiceInformation;
import de.seifi.service_manager.models.ServiceLog;
import de.seifi.service_manager.models.ServiceModel;

import java.util.List;
import java.util.UUID;

public interface IServiceManager {

    ServiceModel getServiceModel();

    void setInterval(Long interval);

    ServiceInformation getServiceInfo();


    List<ServiceLog> getServiceLastLogs();

    void stop();

    void start();
}
