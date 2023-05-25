package de.seifi.wernberg_java.services;

import de.seifi.wernberg_java.enums.ServiceStatus;

import java.util.List;

public interface IService extends Runnable {

    List<String> getErrorList();

    ServiceStatus getStatus();
}
