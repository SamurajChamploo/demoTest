package com.example.demo.service;

import com.example.demo.domain.Managers;
import com.example.demo.repository.ManagersRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ManagersService {
    @Value("${default-time-zone}")
    private String moscowTimeZone;

    private static Logger logger = Logger.getLogger(ManagersService.class.getName());

    private final ManagersRepository managersRepository;

    public ManagersService(ManagersRepository managersRepository) {
        this.managersRepository = managersRepository;
    }

    public String getManagerPhoneNumberByFirstNameEnAndLastNameEn(String firstNameEn, String lastNameEn) {
        Managers manager = managersRepository.findTopByFirstNameEnAndLastNameEn(firstNameEn, lastNameEn);
        if (manager != null) {
            logger.log(Level.INFO, "Getting manager's phone number by {0} and {1}", new Object[] {firstNameEn, lastNameEn});
            return manager.getPhoneNumber();
        }
        logger.log(Level.INFO, "No manager by such {0} and {1}", new Object[] {firstNameEn, lastNameEn});
        return "Нет менеджера по вашим критериям";
    }

    public List<String> getManagers() {
        List<Managers> managers = managersRepository.findAll();
        if (CollectionUtils.isEmpty(managers)) {
            logger.log(Level.INFO, "No managers in DB");
            return List.of("Нет менеджеров в базе");
        }
        logger.log(Level.INFO, "Getting a list of managers' phone numbers");
        return managers.stream()
                .map(Managers::getPhoneNumber)
                .toList();
    }

    public String getManagersByTimeZone(String timeZone) {
        Managers manager = managersRepository.findTopByTimeZone(timeZone); // не находит никого, всегда null возвращает
        if (manager == null) {
            logger.log(Level.INFO, "Getting default Moscow manager");
            return getMoscowManager();
        }
        logger.log(Level.INFO, "Getting manager's phone number by {} time zone", timeZone);
        return manager.getPhoneNumber();
    }

    private String getMoscowManager() {
        Managers managerMoscow = managersRepository.findTopByTimeZone(moscowTimeZone);
        if (managerMoscow == null) {
            logger.log(Level.INFO, "No managers in DB at all");
            return "Нет вообще менеджеров";
        }
        logger.log(Level.INFO, "Getting Moscow manager's phone number");
        return managerMoscow.getPhoneNumber();
    }
}
