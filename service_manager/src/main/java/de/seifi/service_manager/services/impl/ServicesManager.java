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
import de.seifi.service_manager.services.IServicesManager;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServicesManager implements IServicesManager {

    private final ServicesRepository servicesRepository;

    private final Map<UUID, IServiceManager> services;

    public ServicesManager(ServicesRepository servicesRepository) {
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
            services.put(model.getId(), new ServiceManager(model));

        });

    }

    @Override
    public void setSetInterval(UUID serviceId,
                               Long interval) {

        IServiceManager serviceManager = getServiceManagerById(serviceId);

        serviceManager.setInterval(interval);
    }

    @Override
    public List<ServiceModel> getServiceList(){
        return services.values().stream().map(m -> m.getServiceModel()).collect(Collectors.toList());
    }

    @Override
    public List<ServiceInformation> getServiceInfoList(){

        List<ServiceInformation> results = new ArrayList<>();
        for(UUID id: services.keySet()){
            IServiceManager serviceManager = services.get(id);

            results.add(serviceManager.getServiceInfo());
        }

        return results;
    }

    @Override
    public ServiceInformation getServiceInfo(UUID serviceId) {

        if(services.containsKey(serviceId)){
            return services.get(serviceId).getServiceInfo();
        }

        throw new InvalidServiceType();
    }

    @Override
    public List<ServiceLog> getServiceLastLogs(UUID serviceId) {
        IServiceManager serviceManager = getServiceManagerById(serviceId);
        return serviceManager.getServiceLastLogs();
    }

    @Override
    public void stop(UUID serviceId) {
        IServiceManager serviceManager = getServiceManagerById(serviceId);

        serviceManager.stop();
    }

    @Override
    public void start(UUID serviceId) {
        IServiceManager serviceManager = getServiceManagerById(serviceId);

        serviceManager.start();
    }

    private IServiceManager getServiceManagerById(UUID serviceId) {

        IServiceManager serviceManager = null;
        if (services.containsKey(serviceId)) {
            serviceManager = services.get(serviceId);
        } else {
            throw new InvalidServiceType();
        }
        return serviceManager;
    }

}

