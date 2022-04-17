package com.george.orca.domain;

import com.george.orca.domain.enums.ContactType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CONTACT")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONTACT_TYPE")
    private ContactType contactType;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "INFO")
    private String info;

    @Column(name = "ACTIVE")
    private Boolean active = Boolean.FALSE;

}
