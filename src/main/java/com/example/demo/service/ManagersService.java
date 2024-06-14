package com.example.demo.service;

import com.example.demo.domain.Managers;
import com.example.demo.repository.ManagersRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ManagersService {
    @Value("${default-time-zone}")
    private String moscowTimeZone;

    private final ManagersRepository managersRepository;

    private static Logger log = Logger.getLogger(ManagersService.class.getName());

    public ManagersService(ManagersRepository managersRepository) {
        this.managersRepository = managersRepository;
    }

    public String getManagerPhoneNumberByFirstNameEnAndLastNameEn(String firstNameEn, String lastNameEn) {
        Managers manager = managersRepository.findTopByFirstNameEnAndLastNameEn(firstNameEn, lastNameEn);
        if (manager != null) {
            log.info("Getting manager's phone number by their firstNameEn and lastNameEn");
            return manager.getPhoneNumber();
        }
        log.info("No manager by such firstNameEn and lastNameEn");
        return "Нет менеджера по вашим критериям";
    }

    public List<String> getManagers() {
        List<Managers> managers = managersRepository.findAll();
        if (CollectionUtils.isEmpty(managers)) {
            log.info("No managers in DB");
            return List.of("Нет менеджеров в базе");
        }
        log.info("Getting a list of managers' phone numbers");
        return managers.stream()
                .map(Managers::getPhoneNumber)
                .toList();
    }

    public String getManagersByTimeZone(String timeZone) {
        Managers manager = managersRepository.findTopByTimeZone(timeZone); // не находит никого, всегда null возвращает
        if (manager == null) {
            log.info("Getting default Moscow manager");
            return getMoscowManager();
        }
        log.info("Getting manager's phone number by their time zone");
        return manager.getPhoneNumber();
    }

    private String getMoscowManager() {
        Managers managerMoscow = managersRepository.findTopByTimeZone(moscowTimeZone);
        if (managerMoscow == null) {
            log.info("No managers in DB at all");
            return "Нет вообще менеджеров";
        }
        log.info("Getting Moscow manager's phone number");
        return managerMoscow.getPhoneNumber();
    }
}
