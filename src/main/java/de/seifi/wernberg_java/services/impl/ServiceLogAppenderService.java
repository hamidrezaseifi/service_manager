package de.seifi.wernberg_java.services.impl;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import de.seifi.wernberg_java.enums.ServiceType;
import de.seifi.wernberg_java.models.ServiceInformation;
import de.seifi.wernberg_java.models.ServiceLog;
import de.seifi.wernberg_java.utils.ServiceLogAppender;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;

@Service
public class ServiceLogAppenderService {

    private ServiceLogAppender serviceLogAppender;

    public ServiceLogAppenderService() {
    }

    @PostConstruct
    public void init(){
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        for (ch.qos.logback.classic.Logger logger : context.getLoggerList()) {
            for (Iterator<Appender<ILoggingEvent>> index = logger.iteratorForAppenders(); index.hasNext();) {
                Appender<ILoggingEvent> appender = index.next();
                if(appender.getName().equals("SERVICE_LOG_MAP") && appender.getClass().getName().contains("ServiceLogAppender")){
                    this.serviceLogAppender = (ServiceLogAppender)appender;

                }
            }
        }

    }

    public List<ServiceLog> getServiceLastLogs(String serviceTypeName) {
        ServiceType serviceType = ServiceType.fromName(serviceTypeName);
        return this.serviceLogAppender.getServiceLastLogs(serviceType);
    }

}
