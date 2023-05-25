package de.seifi.wernberg_java.services.impl;

import de.seifi.wernberg_java.enums.ServiceStatus;
import de.seifi.wernberg_java.enums.ServiceType;
import de.seifi.wernberg_java.exceptions.InvalidServiceType;
import de.seifi.wernberg_java.models.ServiceInformation;
import de.seifi.wernberg_java.models.ServiceLog;
import de.seifi.wernberg_java.models.ServiceScheduledTask;
import de.seifi.wernberg_java.repositories.Tab02Repository;
import de.seifi.wernberg_java.repositories.Tab03Repository;
import de.seifi.wernberg_java.services.IServiceManager;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ServiceManager implements IServiceManager {

    private Long rankingInitialInterval = 500l;

    private Long progressionInitialInterval = 500l;

    private final TaskScheduler taskScheduler;

    private final Tab02Repository tab02Repository;

    private final Tab03Repository tab03Repository;

    private final Tab03Repository tab04Repository;

    private final Tab01DataReader tab01DataReader;

    private final Map<ServiceType, ServiceScheduledTask> serviceScheduledTasks;

    private final ServiceLogAppenderService logAppenderService;
    
    public ServiceManager(TaskScheduler taskScheduler,
                          Tab02Repository tab02Repository,
                          Tab03Repository tab03Repository,
                          Tab03Repository tab04Repository,
                          Tab01DataReader tab01DataReader,
                          ServiceLogAppenderService logAppenderService) {
        this.taskScheduler = taskScheduler;
        this.tab02Repository = tab02Repository;
        this.tab03Repository = tab03Repository;
        this.tab04Repository = tab04Repository;
        this.tab01DataReader = tab01DataReader;
        this.logAppenderService = logAppenderService;

        serviceScheduledTasks = new HashMap<>();
    }

    @PostConstruct
    public void init(){
        this.initServices();
    }

    private void initServices(){
        if(!serviceScheduledTasks.containsKey(ServiceType.RANKING)){

            ServiceScheduledTask serviceScheduledTask =
                    new ServiceScheduledTask(new RankingService(tab02Repository, tab03Repository, tab01DataReader),
                                             rankingInitialInterval,
                                             taskScheduler);
            serviceScheduledTask.start();
            serviceScheduledTasks.put(ServiceType.RANKING, serviceScheduledTask);
        }
        if(!serviceScheduledTasks.containsKey(ServiceType.PROGRESSION)){

            ServiceScheduledTask serviceScheduledTask =
                    new ServiceScheduledTask(new ProgressionService(tab04Repository, tab01DataReader),
                                             progressionInitialInterval,
                                             taskScheduler);
            serviceScheduledTask.start();
            serviceScheduledTasks.put(ServiceType.PROGRESSION, serviceScheduledTask);
        }

    }

    @Override
    public void setSetInterval(String serviceTypeName,
                               Long interval) {

        ServiceScheduledTask serviceScheduledTask = getServiceScheduledTaskByTypeName(serviceTypeName);

        serviceScheduledTask.setInterval(interval);
    }

    @Override
    public List<ServiceType> getServiceTypes(){
        return serviceScheduledTasks.keySet().stream().collect(Collectors.toList());
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
    public ServiceInformation getServiceInfo(String serviceType) {
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
    public List<ServiceLog> getServiceLastLogs(String serviceType) {
        return this.logAppenderService.getServiceLastLogs(serviceType);
    }

    @Override
    public void stop(String serviceTypeName) {
        ServiceScheduledTask serviceScheduledTask = getServiceScheduledTaskByTypeName(serviceTypeName);

        serviceScheduledTask.stop();
    }

    @Override
    public void start(String serviceTypeName) {
        ServiceScheduledTask serviceScheduledTask = getServiceScheduledTaskByTypeName(serviceTypeName);

        serviceScheduledTask.start();
    }

    private ServiceScheduledTask getServiceScheduledTaskByTypeName(String serviceTypeName) {
        ServiceType serviceType = ServiceType.fromName(serviceTypeName);
        ServiceScheduledTask serviceScheduledTask = null;
        if (serviceScheduledTasks.containsKey(serviceType)) {
            serviceScheduledTask = serviceScheduledTasks.get(serviceType);
        } else {
            throw new InvalidServiceType();
        }
        return serviceScheduledTask;
    }

}

