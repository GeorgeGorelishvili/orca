package com.george.orca.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "PERSON")
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

    @Column(name = "JURIDICAL_PERSON")
    private Boolean juridicalPerson;

    @OneToMany
    @JoinColumn(name = "person_Id")
    private List<PersonContactEntity> contacts;
}
