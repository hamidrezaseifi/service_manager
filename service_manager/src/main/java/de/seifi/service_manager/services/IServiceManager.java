package de.seifi.service_manager.services;

import de.seifi.service_manager.models.ServiceInformation;
import de.seifi.service_manager.models.ServiceLog;
import de.seifi.service_manager.models.ServiceModel;

import java.util.List;
import java.util.UUID;

public interface IServiceManager {

    List<ServiceModel> getServiceList();
    void setSetInterval(UUID serviceId, Long interval);

    List<ServiceInformation> getServiceInfoList();

    ServiceInformation getServiceInfo(UUID serviceId);

    List<ServiceLog> getServiceLastLogs(UUID serviceId);

    void stop(UUID serviceId);

    void start(UUID serviceId);
}
