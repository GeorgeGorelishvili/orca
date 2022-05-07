package com.george.orca.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "LOAN_STATUS")
public class LoanStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "loan_status")
    private String status;


}
