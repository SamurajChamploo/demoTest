package com.example.demo.controller;

import com.example.demo.service.ManagersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/managers")
public class ManagersController {
    private final ManagersService managersService;

    public ManagersController(ManagersService managersService) {
        this.managersService = managersService;
    }

    @GetMapping(value = "/byFirstNameEnAndLastNameEn")
    public String getManagerByFirstNameEnAndLastNameEn(String firstNameEn, String lastNameEn) {
        return managersService.getManagerPhoneNumberByFirstNameEnAndLastNameEn(firstNameEn, lastNameEn);
    }
    @GetMapping
    public List<String> getManagers() {
        return managersService.getManagers();
    }

    @GetMapping(value = "/byTimeZone")
    public String getManagerByTimeZone(String timeZone) {
        return managersService.getManagersByTimeZone(timeZone);
    }
}
