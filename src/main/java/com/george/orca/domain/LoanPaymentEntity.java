package com.george.orca.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "LOAN_PAYMENT")
public class LoanPaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "PAY_DATE")
    private Date date;

    @Column(name = "PAYED")
    private Boolean payed;

}
