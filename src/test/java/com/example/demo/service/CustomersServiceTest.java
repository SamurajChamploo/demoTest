package com.example.demo.service;

import com.example.demo.domain.Customers;
import com.example.demo.repository.CustomersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CustomersServiceTest {
    private CustomersService customersService;
    @Mock
    CustomersRepository customersRepository;

    @BeforeEach
    void setUp() {
        customersService = new CustomersService(customersRepository);
    }

    @Test
    void getCustomersByCnumNegative() {
//        Подготовка
        Long cnum = 123L;
        Mockito.when(customersRepository.findTopByCnum(cnum)).thenReturn(null);
//        Исполнение
        Customers customersByCnum = customersService.getCustomersByCnum(cnum);
//        Проверка
        assertThat(customersByCnum).isEqualTo(null);
    }

    @Test
    void getCustomersByCnumPositive() {
        Long cnum = 123L;
        Customers customer = getTestCustomer(cnum);
        Mockito.when(customersRepository.findTopByCnum(cnum)).thenReturn(customer);

        Customers customersByCnum = customersService.getCustomersByCnum(cnum);

        assertThat(customersByCnum).isEqualTo(customer);
    }

    @Test
    void getCustomersWithAnyList() {
        Long cnum = 123L;
        Customers customer = getTestCustomer(cnum);
        List<Customers> customersList = List.of(customer);
        Mockito.when(customersRepository.findAll()).thenReturn(customersList);

        List<Customers> customersAll = customersService.getCustomers();

        assertThat(customersAll).isEqualTo(customersList);
    }

    @Test
    void getCustomersWithEmptyList() {
        List<Customers> customersList = List.of();
        Mockito.when(customersRepository.findAll()).thenReturn(customersList);

        List<Customers> customersAll = customersService.getCustomers();

        assertThat(customersAll).isEqualTo(customersList);
    }

    private Customers getTestCustomer(Long cnum) {
        Customers customer = new Customers();
        customer.setCnum(cnum);
        customer.setCreatedDate(LocalDate.now());
        customer.setFirstName("Petr");
        customer.setId(2L);
        customer.setPhoneNumber("1234567");
        customer.setLastName("Petrov");
        customer.setCreatedDate(LocalDate.now());
        return customer;
    }
}