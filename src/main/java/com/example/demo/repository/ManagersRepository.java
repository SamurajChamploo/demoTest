package com.example.demo.repository;

import com.example.demo.domain.Managers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagersRepository extends JpaRepository<Managers, Long> {
    Managers findByFirstNameEnAndLastNameEn(String firstNameEn, String lastNameEn);
}
