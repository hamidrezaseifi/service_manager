package de.seifi.wernberg_java.controllers;

import de.seifi.wernberg_java.models.ServiceInformation;
import de.seifi.wernberg_java.models.ServiceLog;
import de.seifi.wernberg_java.services.IServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {

    private final IServiceManager serviceManager;

    public ServiceController(IServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @GetMapping("/service/{serviceType}/ranking_interval/{interval}")
    public ResponseEntity<Long> rankingInterval(@PathVariable String serviceType, @PathVariable Long interval) {
        this.serviceManager.setSetInterval(serviceType, interval);

        return ResponseEntity.ok(interval);
    }

    @GetMapping("/service/{serviceType}/logs")
    public ResponseEntity<List<ServiceLog>> rankingLogs(@PathVariable String serviceType) {
        List<ServiceLog> logs = this.serviceManager.getServiceLastLogs(serviceType);

        return ResponseEntity.ok(logs);
    }
    @GetMapping("/service/{serviceType}/info")
    public ResponseEntity<ServiceInformation> getServiceInfo(@PathVariable String serviceType) {
        ServiceInformation info = this.serviceManager.getServiceInfo(serviceType);

        return ResponseEntity.ok(info);
    }

    @GetMapping("/service/{serviceType}/stop")
    public ResponseEntity<Void> stopService(@PathVariable String serviceType) {
        this.serviceManager.stop(serviceType);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/service/{serviceType}/start")
    public ResponseEntity<Void> startService(@PathVariable String serviceType) {
        this.serviceManager.start(serviceType);

        return ResponseEntity.ok(null);
    }
    @GetMapping("/services/list")
    public ResponseEntity<List<ServiceInformation>> listServices() {

        List<ServiceInformation> services = this.serviceManager.getServiceInfoList();

        return ResponseEntity.ok(services);
    }

}

