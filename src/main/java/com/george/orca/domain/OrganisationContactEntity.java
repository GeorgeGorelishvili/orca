package com.george.orca.domain;

import com.george.orca.domain.enums.ContactType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ORGANISATION_CONTACT")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganisationContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CONTACT_TYPE")
    private String contactType;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "phone")
    private String phone;

    @Column(name = "ORGANIZATION_ID")
    private Long organizationId;

    @Column(name = "contact_legal_address")
    private String legalAddress;

    @Column(name = "contact_physical_address")
    private String physicalAddress;

    @Column(name = "e_mail")
    private String mail;

}
