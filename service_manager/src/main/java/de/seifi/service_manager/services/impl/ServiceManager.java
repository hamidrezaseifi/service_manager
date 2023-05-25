package de.seifi.service_manager.services.impl;

import de.seifi.service_manager.entities.ServicesEntity;
import de.seifi.service_manager.enums.ServiceStatus;
import de.seifi.service_manager.exceptions.InvalidServiceType;
import de.seifi.service_manager.models.ServiceInformation;
import de.seifi.service_manager.models.ServiceLog;
import de.seifi.service_manager.models.ServiceModel;
import de.seifi.service_manager.models.ServiceScheduledTask;
import de.seifi.service_manager.repositories.ServicesRepository;
import de.seifi.service_manager.services.IServiceManager;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceManager implements IServiceManager {

    private final ServicesRepository servicesRepository;

    private final Map<UUID, ServiceModel> services;

    public ServiceManager(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;

        services = new HashMap<>();
    }

    @PostConstruct
    public void init(){
        this.initServices();
    }

    private void initServices(){
        services.clear();

        List<ServicesEntity> serviceEntities = this.servicesRepository.findAllByStatus(1);

        serviceEntities.forEach(e -> {
            ServiceModel model = new ServiceModel(e);
            services.put(model.getId(), model);

        });

    }

    @Override
    public void setSetInterval(UUID serviceId,
                               Long interval) {

        ServiceScheduledTask serviceScheduledTask = getServiceById(serviceId);

        serviceScheduledTask.setInterval(interval);
    }

    @Override
    public List<ServiceModel> getServiceList(){
        return services.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<ServiceInformation> getServiceInfoList(){
        List<ServiceType> types = getServiceTypes();

        List<ServiceInformation> results = new ArrayList<>();
        for(ServiceType type: types){
            ServiceStatus status = serviceScheduledTasks.get(type).getStatus();

            results.add(new ServiceInformation(type, status));
        }

        return results;
    }

    @Override
    public ServiceInformation getServiceInfo(UUID serviceId) {
        List<ServiceInformation> list = getServiceInfoList();
        for(ServiceInformation info: list){
            if(info.getType().toString().equals(serviceType) ){

                info.setLogs(getServiceLastLogs(serviceType));

                return info;
            }

        }

        throw new InvalidServiceType();
    }

    @Override
    public List<ServiceLog> getServiceLastLogs(UUID serviceId) {
        return this.logAppenderService.getServiceLastLogs(serviceId);
    }

    @Override
    public void stop(UUID serviceId) {
        ServiceScheduledTask serviceScheduledTask = getServiceById(serviceId);

        serviceScheduledTask.stop();
    }

    @Override
    public void start(UUID serviceId) {
        ServiceScheduledTask serviceScheduledTask = getServiceById(serviceId);

        serviceScheduledTask.start();
    }

    private ServiceModel getServiceById(UUID serviceId) {

        ServiceModel serviceModel = null;
        if (services.containsKey(serviceId)) {
            serviceModel = services.get(serviceId);
        } else {
            throw new InvalidServiceType();
        }
        return serviceModel;
    }

}

