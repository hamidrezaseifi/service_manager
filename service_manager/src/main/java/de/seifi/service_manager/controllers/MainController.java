package de.seifi.service_manager.controllers;

import de.seifi.service_manager.services.impl.ServicesManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {



    private final ServicesManager applicationPropertyService;

    public MainController(ServicesManager applicationPropertyService) {
        this.applicationPropertyService = applicationPropertyService;
    }

    @GetMapping("/test")
    public String listTab1() {

        return "Test String ...";
    }




}

