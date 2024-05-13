package com.example.demo.service;

import com.example.demo.domain.Managers;
import com.example.demo.repository.ManagersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ManagersServiceTest {
    private ManagersService managersService;

    @Mock
    ManagersRepository managersRepository;

    @BeforeEach
    void setUp() {
        managersService = new ManagersService(managersRepository);
    }

    @Test
    void getManagerPhoneNumberByFirstNameEnAndLastNameEnPositive() {
        String firstNameEn = "Petr";
        String lastNameEn = "Petrov";
        Managers manager = getTestManager(firstNameEn, lastNameEn);
        String expectedPhoneNumber = manager.getPhoneNumber();
        Mockito.when(managersRepository.findTopByFirstNameEnAndLastNameEn(firstNameEn, lastNameEn)).thenReturn(manager);

        String managerByFirstNameEnAndLastNameEn = managersService.getManagerPhoneNumberByFirstNameEnAndLastNameEn(firstNameEn, lastNameEn);

        assertThat(managerByFirstNameEnAndLastNameEn).isEqualTo(expectedPhoneNumber);
    }

    @Test
    void getManagerPhoneNumberByFirstNameEnAndLastNameEnNull() {
        String firstNameEn = "Petr";
        String lastNameEn = "Petrov";
        Mockito.when(managersRepository.findTopByFirstNameEnAndLastNameEn(Mockito.anyString(), Mockito.anyString())).thenReturn(null);

        String managerByFirstNameEnAndLastNameEn = managersService.getManagerPhoneNumberByFirstNameEnAndLastNameEn(firstNameEn, lastNameEn);

        assertThat(managerByFirstNameEnAndLastNameEn).isEqualTo("Нет менеджера по вашим критериям");
    }

    @Test
    void getManagerPhoneNumberByFirstNameEnAndLastNameEnBothNull() {
        Mockito.when(managersRepository.findTopByFirstNameEnAndLastNameEn(Mockito.any(), Mockito.any())).thenReturn(null);

        String managerByFirstNameEnAndLastNameEn = managersService.getManagerPhoneNumberByFirstNameEnAndLastNameEn(null, null);

        assertThat(managerByFirstNameEnAndLastNameEn).isEqualTo("Нет менеджера по вашим критериям");
    }

    @Test
    void getManagerPhoneNumberListByFirstNameEnAndLastNameEnPositive() {
        String firstNameEn = "Petr";
        String lastNameEn = "Petrov";
        List<Managers> managers = List.of(getTestManager(firstNameEn, lastNameEn));
        List<String> expectedPhoneNumberList = managers.stream()
                .map(Managers::getPhoneNumber)
                .toList();
        Mockito.when(managersRepository.findAll()).thenReturn(managers);

        List<String> managersPhoneNumbers = managersService.getManagers();

        assertThat(managersPhoneNumbers).isEqualTo(expectedPhoneNumberList);
    }

    @Test
    void getManagerPhoneNumberListByFirstNameEnAndLastNameEmpty() {
        List<String> expectedPhoneNumberList = List.of("Нет менеджеров в базе");
        Mockito.when(managersRepository.findAll()).thenReturn(List.of());

        List<String> managersPhoneNumbers = managersService.getManagers();

        assertThat(managersPhoneNumbers).isEqualTo(expectedPhoneNumberList);
    }

    @Test
    void getManagerPhoneNumberByTimeZonePositive() {
        String timeZone = "+02:00";
        Managers manager = getTestManager2(timeZone);
        String expectedPhoneNumber = manager.getPhoneNumber();
        Mockito.when(managersRepository.findTopByTimeZone(timeZone)).thenReturn(manager);

        String managersPhoneNumber = managersService.getManagersByTimeZone(timeZone);

        assertThat(managersPhoneNumber).isEqualTo(expectedPhoneNumber);
    }

    @Test
    void getManagerPhoneNumberByTimeZoneNull() {
        String timeZone = "+02:00";
        Managers manager = getTestManager2("+03:00");
        String expectedPhoneNumber = manager.getPhoneNumber();
        Mockito.when(managersRepository.findTopByTimeZone(timeZone)).thenReturn(null);
        Mockito.when(managersRepository.findTopByTimeZone("+03:00")).thenReturn(manager);

        String managersPhoneNumber = managersService.getManagersByTimeZone(timeZone);

        assertThat(managersPhoneNumber).isEqualTo(expectedPhoneNumber);
    }

    @Test
    void getManagerPhoneNumberByTimeZoneTotallyNull() {
        String timeZone = "+02:00";
        Managers manager = getTestManager2("+03:00");
        Mockito.when(managersRepository.findTopByTimeZone(timeZone)).thenReturn(null);
        Mockito.when(managersRepository.findTopByTimeZone("+03:00")).thenReturn(null);

        String managersPhoneNumber = managersService.getManagersByTimeZone(timeZone);

        assertThat(managersPhoneNumber).isEqualTo("Нет вообще менеджеров");
    }

    private Managers getTestManager(String firstNameEn, String lastNameEn) {
        Managers manager = new Managers();
        manager.setFirstNameEn(firstNameEn);
        manager.setFirstNameRu("Петр");
        manager.setLastNameEn(lastNameEn);
        manager.setLastNameRu("Петров");
        manager.setMiddleNameEn("Petrovich");
        manager.setMiddleNameRu("Петрович");
        manager.setPhoneNumber("1231231");
        manager.setTimeZone("+03:00");
        return manager;
    }

    private Managers getTestManager2(String timeZone) {
        Managers manager = new Managers();
        manager.setFirstNameEn("Petr");
        manager.setFirstNameRu("Петр");
        manager.setLastNameEn("Petrov");
        manager.setLastNameRu("Петров");
        manager.setMiddleNameEn("Petrovich");
        manager.setMiddleNameRu("Петрович");
        manager.setPhoneNumber("1231231");
        manager.setTimeZone(timeZone);
        return manager;
    }
}
