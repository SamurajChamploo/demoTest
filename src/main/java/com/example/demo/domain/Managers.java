package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "managers")
@ToString
@Getter
@Setter
public class Managers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name_ru", nullable = false)
    private String firstNameRu;
    @Column(name = "last_name_ru", nullable = false)
    private String lastNameRu;
    @Column(name = "middle_name_ru", nullable = false)
    private String middleNameRu;
    @Column(name = "first_name_en", nullable = false)
    private String firstNameEn;
    @Column(name = "last_name_en", nullable = false)
    private String lastNameEn;
    @Column(name = "middle_name_en", nullable = false)
    private String middleNameEn;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "time_zone")
    private String timeZone;
}
