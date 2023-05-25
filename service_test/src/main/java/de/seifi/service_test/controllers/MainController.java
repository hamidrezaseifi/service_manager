package de.seifi.service_test.controllers;

import de.seifi.service_test.entities.*;
import de.seifi.service_test.repositories.*;
import de.seifi.service_test.services.impl.ServiceManager;
import de.seifi.service_test.services.impl.ServiceLogAppenderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    private final Tab01Repository tab01Repository;

    private final Tab02Repository tab02Repository;

    private final Tab03Repository tab03Repository;

    private final Tab04Repository tab04Repository;

    private final ServiceLogAppenderService serviceLogAppenderService;

    private final ServiceManager applicationPropertyService;

    public MainController(Tab01Repository tab01Repository,
                          Tab02Repository tab02Repository,
                          Tab03Repository tab03Repository,
                          Tab04Repository tab04Repository,
                          ServiceLogAppenderService serviceLogAppenderService,
                          ServiceManager applicationPropertyService) {
        this.tab01Repository = tab01Repository;
        this.tab02Repository = tab02Repository;
        this.tab03Repository = tab03Repository;
        this.tab04Repository = tab04Repository;
        this.serviceLogAppenderService = serviceLogAppenderService;
        this.applicationPropertyService = applicationPropertyService;
    }

    @GetMapping("/tab01")
    public List<Tab01Entity> listTab1() {
        List<Tab01Entity> result = new ArrayList<>();
        this.tab01Repository.findAll().forEach(result::add);

        return result;
    }

    @GetMapping("/tab02")
    public List<Tab02Entity> listTab2() {
        return this.tab02Repository.findAll();
    }

    @GetMapping("/tab03")
    public List<Tab03Entity> listTab3() {
        return this.tab03Repository.findAll();
    }

    @GetMapping("/tab04")
    public List<Tab04Entity> listTab4() {
        return this.tab04Repository.findAll();
    }



}

