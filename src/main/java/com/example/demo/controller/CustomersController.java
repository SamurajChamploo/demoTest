package com.example.demo.controller;

import com.example.demo.domain.Customers;
import com.example.demo.service.CustomersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomersController {
    private final CustomersService customersService;

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping(value = "/byCnum")
    public String getCustomersByCnum(Long cnum) {
        Customers customers = customersService.getCustomersByCnum(cnum);
        return customers.getFirstName();
    }

    @GetMapping
    public List<String> getCustomers() {
        List<Customers> customers = customersService.getCustomers();
        return customers.stream()
                .map(Customers::getFirstName)
                .toList();
    }
}
