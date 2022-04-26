package com.george.orca.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORGANIZATION")
public class OrganizationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CADASTRIAL_CODE")
    private String organizationName;

    @Column(name = "ORGANISATION_NAME")
    private String orgName;

    @Column(name = "WORKING_FIELD")
    private String workingField;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "MAIN_ACCOUNTANT")
    private String mainAccountant;

    @Column(name = "PHONE_NUMBER")
    private Integer phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BANK_ACCOUNT")
    private String bankAccount;

    @Column(name = "PHYSICAL_ADDRESS")
    private String physicalAddress;

    @Column(name = "LEGAL_ADDRESS")
    private String legalAddress;

}
