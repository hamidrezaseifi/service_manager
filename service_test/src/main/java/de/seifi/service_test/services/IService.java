package de.seifi.service_test.services;

import de.seifi.service_test.enums.ServiceStatus;

import java.util.List;

public interface IService extends Runnable {

    List<String> getErrorList();

    ServiceStatus getStatus();
}
