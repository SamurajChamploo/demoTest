package com.example.demo.service;

import com.example.demo.domain.Customers;
import com.example.demo.repository.CustomersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomersService {
    private final CustomersRepository customersRepository;

    public CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public Customers getCustomersByCnum(Long cnum) {
        Customers customer = customersRepository.findTopByCnum(cnum);
        if (customer != null) {
            log.info("Getting a customer by their cnum");
            return customer;
        }
        log.error("No customer with such cnum");
        return null;
    }

    public List<Customers> getCustomers() {
        List<Customers> customers = customersRepository.findAll();
        log.info("Getting a list of customers");
        return customers;
    }

}
