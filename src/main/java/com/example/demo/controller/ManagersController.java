package com.example.demo.controller;

import com.example.demo.service.ManagersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/managers")
public class ManagersController {
    private final ManagersService managersService;

    private static Logger log = Logger.getLogger(ManagersController.class.getName());

    public ManagersController(ManagersService managersService) {
        this.managersService = managersService;
    }

    @GetMapping(value = "/byFirstNameEnAndLastNameEn")
    public String getManagerByFirstNameEnAndLastNameEn(String firstNameEn, String lastNameEn) {
        log.info("Getting manager's phone number by its firstNameEn and lastNameEn");
        return managersService.getManagerPhoneNumberByFirstNameEnAndLastNameEn(firstNameEn, lastNameEn);
    }
    @GetMapping
    public List<String> getManagers() {
        log.info("Getting a list of managers' phone numbers");
        return managersService.getManagers();
    }

    @GetMapping(value = "/byTimeZone")
    public String getManagerByTimeZone(String timeZone) {
        log.info("Getting manager's phone number by their time zone");
        return managersService.getManagersByTimeZone(timeZone);
    }
}
