package com.example.demo.controller;

import com.example.demo.service.ManagersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/managers")
public class ManagersController {
    private final ManagersService managersService;

    public ManagersController(ManagersService managersService) {
        this.managersService = managersService;
    }

    @GetMapping(value = "/byFirstNameEnAndLastNameEn")
    public String getManagersByFirstNameEnAndLastNameEn(String firstNameEn, String lastNameEn) {
        return managersService.getManagersByFirstNameEnAndLastNameEn(firstNameEn, lastNameEn);
    }
//    @GetMapping
//    public String getManagers() {
//        return managersService.getManagers();
//    }
}
