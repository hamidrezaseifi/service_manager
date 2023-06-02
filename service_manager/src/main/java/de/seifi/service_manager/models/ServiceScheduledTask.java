package de.seifi.service_manager.models;

import de.seifi.service_manager.enums.ServiceStatus;

public class ServiceScheduledTask {

    private Long interval;

    private Object runnable;

    public ServiceScheduledTask(Object runnable) {
        this.runnable = runnable;
        this.interval = 0L;
    }

    public Object getRunnable() {
        return runnable;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
        stop();
        start();
    }

    public void stop(){
        if(runnable != null){

        }
    }

    public void start(){
        stop();

    }

    public ServiceStatus getStatus(){
        if(runnable == null){
            return ServiceStatus.IDLE;
        }

        ServiceStatus status = ServiceStatus.RUNNUNG;


        return status;
    }
}
