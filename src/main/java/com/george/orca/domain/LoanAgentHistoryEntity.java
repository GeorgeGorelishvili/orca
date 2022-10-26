package com.george.orca.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "LOAN_AGENT_HISTORY")
@NoArgsConstructor
@AllArgsConstructor
public class LoanAgentHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "loan_id")
    private Long loanId;

    @OneToOne
    private EmployeeEntity employee;

    @Column(name = "date")
    private Date date;

    @Column(name = "employee_status")
    private String status;
}
