package de.seifi.service_test.services;

import de.seifi.service_test.enums.ServiceType;
import de.seifi.service_test.exceptions.InvalidServiceType;
import de.seifi.service_test.models.ServiceInformation;
import de.seifi.service_test.models.ServiceLog;
import de.seifi.service_test.models.ServiceScheduledTask;

import java.util.List;

public interface IServiceManager {
    void setSetInterval(String serviceTypeName, Long interval);

    List<ServiceType> getServiceTypes();

    List<ServiceInformation> getServiceInfoList();

    ServiceInformation getServiceInfo(String serviceType);

    List<ServiceLog> getServiceLastLogs(String serviceType);

    void stop(String serviceTypeName);

    void start(String serviceTypeName);
}
