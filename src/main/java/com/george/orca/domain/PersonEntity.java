package com.george.orca.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "PERSON")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FIRSTNAME", length = 50)
    private String firstname;

    @Column(name = "LASTNAME", length = 50)
    private String lastname;

    @Column(name = "PERSONAL_NUMBER", length = 11)
    private String personalNumber;

    @Column(name = "LEGAL_ADDRESS")
    private String legalAddress;

    @Column(name = "PHYSICAL_ADDRESS")
    private String physicalAddress;

    @Column(name = "phone")
    private String phone;

    @Column(name = "JURIDICAL_PERSON")
    private Boolean juridicalPerson;

    @OneToMany
    @JoinColumn(name = "person_Id")
    private List<PersonContactEntity> contacts;
}
