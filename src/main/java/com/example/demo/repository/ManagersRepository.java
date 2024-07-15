package com.example.demo.repository;

import com.example.demo.domain.Managers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagersRepository extends JpaRepository<Managers, Long> {
    @Query(value = "select * from managers.managers m where first_Name_En =: firstNameEn and last_Name_En =: lastNameEn limit 1", nativeQuery = true)
    Managers findTopByFirstNameEnAndLastNameEn(String firstNameEn, String lastNameEn);
    @Query("select m from Managers m where m.timeZone= :timeZone")
    Managers findTopByTimeZone(@Param("timeZone") String timeZone);
}

