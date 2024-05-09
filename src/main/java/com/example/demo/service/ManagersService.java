package com.example.demo.service;

import com.example.demo.domain.Managers;
import com.example.demo.repository.ManagersRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ManagersService {
    private final ManagersRepository managersRepository;

    public ManagersService(ManagersRepository managersRepository) {
        this.managersRepository = managersRepository;
    }

    public String getManagersByFirstNameEnAndLastNameEn(String firstNameEn, String lastNameEn) {
        Managers managers = managersRepository.findByFirstNameEnAndLastNameEn(firstNameEn, lastNameEn);
        if (managers != null) {
            return managers.getPhoneNumber();
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
}
