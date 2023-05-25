package de.seifi.wernberg_java.services.impl;

import de.seifi.wernberg_java.enums.ServiceStatus;
import de.seifi.wernberg_java.repositories.Tab03Repository;
import de.seifi.wernberg_java.services.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

//@Service
public class ProgressionService implements IService {

    private static final Logger logger = LoggerFactory.getLogger(ProgressionService.class);

    private final Tab03Repository tab04Repository;

    private final Tab01DataReader tab01DataReader;

    private final List<String> errorList;

    private ServiceStatus status;

    public ProgressionService(Tab03Repository tab04Repository,
                              Tab01DataReader tab01DataReader) {
        this.tab04Repository = tab04Repository;
        this.tab01DataReader = tab01DataReader;

        this.errorList = new ArrayList<>();

        this.status = ServiceStatus.IDLE;
    }

    @Override
    public void run() {
        this.status = ServiceStatus.RUNNUNG;

        logger.info("Running ProgressionService!!!");

        this.status = ServiceStatus.STOPPED;

    }

    @Override
    public List<String> getErrorList() {
        return errorList;
    }

    @Override
    public ServiceStatus getStatus() {
        return this.status;
    }
}
