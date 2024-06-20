package com.example.demo.controller;

import com.example.demo.service.ManagersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/managers")
public class ManagersController {
    private final ManagersService managersService;

    private static Logger logger = Logger.getLogger(ManagersController.class.getName());

    public ManagersController(ManagersService managersService) {
        this.managersService = managersService;
    }

    @GetMapping(value = "/byFirstNameEnAndLastNameEn")
    public String getManagerByFirstNameEnAndLastNameEn(String firstNameEn, String lastNameEn) {
        logger.log(Level.INFO, "Getting manager's phone number by {0} and {1}", new Object[] {firstNameEn, lastNameEn});
        return managersService.getManagerPhoneNumberByFirstNameEnAndLastNameEn(firstNameEn, lastNameEn);
    }
    @GetMapping
    public List<String> getManagers() {
        logger.log(Level.INFO, "Getting a list of managers' phone numbers");
        return managersService.getManagers();
    }

    @GetMapping(value = "/byTimeZone")
    public String getManagerByTimeZone(String timeZone) {
        logger.log(Level.INFO, "Getting manager's phone number by {} time zone", timeZone);
        return managersService.getManagersByTimeZone(timeZone);
    }
}
