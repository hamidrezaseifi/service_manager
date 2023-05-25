package de.seifi.service_manager.models;

import de.seifi.service_manager.enums.ServiceStatus;
import de.seifi.service_manager.services.IService;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ServiceScheduledTask {

    private Long interval;

    private ScheduledFuture scheduledFuture;

    private PeriodicTrigger trigger;

    private IService runnable;

    private final TaskScheduler taskScheduler;

    public ServiceScheduledTask(IService runnable,
                                Long interval,
                                TaskScheduler taskScheduler) {
        this.runnable = runnable;
        this.interval = interval;
        this.taskScheduler = taskScheduler;
    }

    public ScheduledFuture getScheduledFuture() {
        return scheduledFuture;
    }

    public PeriodicTrigger getTrigger() {
        return trigger;
    }

    public IService getRunnable() {
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
        if(scheduledFuture != null){
            scheduledFuture.cancel(true);
            this.trigger = null;
            this.scheduledFuture = null;
        }
    }

    public void start(){
        stop();
        this.trigger = new PeriodicTrigger(this.interval, TimeUnit.MILLISECONDS);
        this.scheduledFuture = taskScheduler.schedule(this.runnable, this.trigger);
    }

    public ServiceStatus getStatus(){
        if(scheduledFuture == null){
            return ServiceStatus.IDLE;
        }

        ServiceStatus status = ServiceStatus.RUNNUNG;
        if(scheduledFuture.isCancelled()){
            status = ServiceStatus.STOPPED;
        }
        if(scheduledFuture.isDone()){
            status = ServiceStatus.STOPPED;
        }

        return status;
    }
}
