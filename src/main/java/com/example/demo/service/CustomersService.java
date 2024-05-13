package com.example.demo.service;

import com.example.demo.domain.Customers;
import com.example.demo.repository.CustomersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {
    private final CustomersRepository customersRepository;

    public CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public Customers getCustomersByCnum(Long cnum) {
        Customers customer = customersRepository.findTopByCnum(cnum);
        if (customer != null) {
            return customer;
        }
        return null;
    }

    public List<Customers> getCustomers() {
        List<Customers> customers = customersRepository.findAll();
        return customers;
    }

}
