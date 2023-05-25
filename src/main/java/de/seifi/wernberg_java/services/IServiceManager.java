package de.seifi.wernberg_java.services;

import de.seifi.wernberg_java.enums.ServiceType;
import de.seifi.wernberg_java.exceptions.InvalidServiceType;
import de.seifi.wernberg_java.models.ServiceInformation;
import de.seifi.wernberg_java.models.ServiceLog;
import de.seifi.wernberg_java.models.ServiceScheduledTask;

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
