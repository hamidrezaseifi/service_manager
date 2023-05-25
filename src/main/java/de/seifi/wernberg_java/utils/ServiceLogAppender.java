package de.seifi.wernberg_java.utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import de.seifi.wernberg_java.enums.ServiceType;
import de.seifi.wernberg_java.models.ServiceLog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


public class ServiceLogAppender extends AppenderBase<ILoggingEvent> {

    private static final int MAX_CACHING_LOG_COUNT = 200;
    private final Map<ServiceType, List<ServiceLog>> serviceLogs;

    private final DateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ServiceLogAppender() {
        this.serviceLogs = new HashMap<>();
        this.serviceLogs.put(ServiceType.RANKING, new ArrayList<>());
        this.serviceLogs.put(ServiceType.PROGRESSION, new ArrayList<>());
    }


    @Override
    protected void append(ILoggingEvent loggingEvent) {
        if(loggingEvent.getLoggerName().contains("RankingService")){
            addLog(ServiceType.RANKING, loggingEvent);
        }
        if(loggingEvent.getLoggerName().contains("ProgressionService")){
            addLog(ServiceType.PROGRESSION, loggingEvent);
        }
    }

    private void addLog(ServiceType serviceType,
                        ILoggingEvent loggingEvent) {
        Date logDateTime = new Date(loggingEvent.getTimeStamp());
        String dateString = dateTimeFormatter.format(logDateTime);
        loggingEvent.getFormattedMessage();
        this.serviceLogs.get(serviceType).add(0, new ServiceLog(loggingEvent.getFormattedMessage(),
                                                                loggingEvent.getLevel().toString(),
                                                                logDateTime,
                                                                dateString));
        while(this.serviceLogs.get(serviceType).size() > MAX_CACHING_LOG_COUNT){
            int size = this.serviceLogs.get(serviceType).size();
            this.serviceLogs.get(serviceType).remove(size -1);
        }
    }

    public List<ServiceLog> getServiceLastLogs(ServiceType serviceType){

        return this.serviceLogs.get(serviceType);
    }
}
