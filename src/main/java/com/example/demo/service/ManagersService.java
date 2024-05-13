package com.example.demo.service;

import com.example.demo.domain.Managers;
import com.example.demo.repository.ManagersRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ManagersService {
    private static final String MOSCOW_TIME_ZONE = "+03:00";

    private final ManagersRepository managersRepository;

    public ManagersService(ManagersRepository managersRepository) {
        this.managersRepository = managersRepository;
    }

    public String getManagerPhoneNumberByFirstNameEnAndLastNameEn(String firstNameEn, String lastNameEn) {
        Managers manager = managersRepository.findTopByFirstNameEnAndLastNameEn(firstNameEn, lastNameEn);
        if (manager != null) {
            return manager.getPhoneNumber();
        }
        return "Нет менеджера по вашим критериям";
    }

    public List<String> getManagers() {
        List<Managers> managers = managersRepository.findAll();
        if (CollectionUtils.isEmpty(managers)) {
            return List.of("Нет менеджеров в базе");
        }
        return managers.stream()
                .map(Managers::getPhoneNumber)
                .toList();
    }

    public String getManagersByTimeZone(String timeZone) {
        Managers manager = managersRepository.findTopByTimeZone(timeZone);
        if (manager == null) {
            return getMoscowManager();
        }
        return manager.getPhoneNumber();
    }

    private String getMoscowManager() {
        Managers managerMoscow = managersRepository.findTopByTimeZone(MOSCOW_TIME_ZONE);
        if (managerMoscow == null) {
            return "Нет вообще менеджеров";
        }
        return managerMoscow.getPhoneNumber();
    }
}
