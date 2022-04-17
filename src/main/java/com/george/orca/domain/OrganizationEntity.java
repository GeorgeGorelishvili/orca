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

    @Column(name = "LOAN_NUMBER")
    private Long loanNumber;

    @Column(name = "IDENTIFICATION_CODE")
    private String organizationName;

    @Column(name = "PERSONAL_NUMBER")
    private String personalNumber;

    @Column(name = "LOAN_AMOUNT")
    private BigDecimal loanAmount;

    @Column(name = "RESPONSIBLE_PERSON")
    private String responsiblePerson;

    @Column(name = "LEGAL_ADDRESS")
    private String legalAddress;

    @Column(name = "PHYSICAL_ADDRESS")
    private String physicalAddress;
}
