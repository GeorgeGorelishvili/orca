package com.george.orca.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(name="loan_id")
    private Long loanId;

//    @Column(name="payment_type")


    @Column(name="comment", length = 10000)
    private String comment;

}
