package com.george.orca.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SYSTEM_USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "DEACTIVATE_DATE")
    private Date deactivateDate;

    @OneToOne
    private EmployeeEntity employeeEntity;
}
