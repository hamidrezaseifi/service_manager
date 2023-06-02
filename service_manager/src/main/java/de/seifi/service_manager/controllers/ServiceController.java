package de.seifi.service_manager.controllers;

import de.seifi.service_manager.models.ServiceInformation;
import de.seifi.service_manager.models.ServiceLog;
import de.seifi.service_manager.services.IServicesManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ServiceController {

    private final IServicesManager serviceManager;

    public ServiceController(IServicesManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @GetMapping("/service/{serviceId}/interval/{interval}")
    public ResponseEntity<Long> rankingInterval(@PathVariable UUID serviceId, @PathVariable Long interval) {
        this.serviceManager.setSetInterval(serviceId, interval);

        return ResponseEntity.ok(interval);
    }

    @GetMapping("/service/{serviceId}/logs")
    public ResponseEntity<List<ServiceLog>> rankingLogs(@PathVariable UUID serviceId) {
        List<ServiceLog> logs = this.serviceManager.getServiceLastLogs(serviceId);

        return ResponseEntity.ok(logs);
    }
    @GetMapping("/service/{serviceId}/info")
    public ResponseEntity<ServiceInformation> getServiceInfo(@PathVariable UUID serviceId) {
        ServiceInformation info = this.serviceManager.getServiceInfo(serviceId);

        return ResponseEntity.ok(info);
    }

    @GetMapping("/service/{serviceId}/stop")
    public ResponseEntity<Void> stopService(@PathVariable UUID serviceId) {
        this.serviceManager.stop(serviceId);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/service/{serviceId}/start")
    public ResponseEntity<Void> startService(@PathVariable UUID serviceId) {
        this.serviceManager.start(serviceId);

        return ResponseEntity.ok(null);
    }
    @GetMapping("/services/list")
    public ResponseEntity<List<ServiceInformation>> listServices() {

        List<ServiceInformation> services = this.serviceManager.getServiceInfoList();

        return ResponseEntity.ok(services);
    }

}

