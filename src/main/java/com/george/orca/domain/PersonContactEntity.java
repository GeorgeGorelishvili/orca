package com.george.orca.domain;

import com.george.orca.domain.enums.ContactType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PERSON_CONTACT")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CONTACT_TYPE")
    private String contactType;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name ="person_id")
    private Long personId;

}
