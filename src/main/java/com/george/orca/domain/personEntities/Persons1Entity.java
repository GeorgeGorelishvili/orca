package com.george.orca.domain.personEntities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "persons_1")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persons1Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FIRSTNAME", length = 150)
    private String firstname;

    @Column(name = "LASTNAME", length = 150)
    private String lastname;

    @Column(name = "PERSONAL_NUMBER", length = 11)
    private String personalNumber;

    @Column(name = "YEAR")
    private String birthYear;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "physical_address")
    private String physicalAddress;

    @Column(name = "legal_address")
    private String legalAddress;

}