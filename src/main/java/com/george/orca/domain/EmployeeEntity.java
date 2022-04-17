package com.george.orca.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonSerialize
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FIRST_NAME", columnDefinition = "nvarchar(30)")
    private String firstName;

    @Column(name = "LAST_NAME", length = 20)
    private String lastName;

    @Column(name = "PERSONAL_NUMBER", length = 11)
    private String personalNumber;

    @Column(name = "ADDRESS", length = 4000)
    private String address;

    @Column(name = "BIRTHDATE")
    private Date birthDate;

    @Column(name = "MOBILE_NUMBER", length = 20)
    private String mobileNumber;

    // TODO [GG] 6.ოჯახის წევრი და მისი საკონტაქტო ნომერი
    @Column(name = "CONNECTED_PERSON", length = 500)
    private String connectedPerson;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "ACCOUNT_NUMBER", length = 50)
    private String accountNumber;

    @ManyToOne
    private EmployeePositionEntity employeePosition;
}
