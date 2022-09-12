package com.george.orca.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "LOAN")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "INITIAL_AMOUNT")
    private BigDecimal initialAmount;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @ManyToOne
    private OrganizationEntity debtorOrganization;

    @ManyToOne
    private OrganizationEntity creditorOrganization;

    @ManyToOne
    private PersonEntity debtorPerson;

    @ManyToOne
    private PersonEntity creditorPerson;

    @Column(name = "INCOME_DATE")
    private Date incomeDate;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "FINISHED", nullable = false, columnDefinition = "boolean default false")
    private boolean finished = Boolean.FALSE;

    @Column(name = "DELETED", nullable = false, columnDefinition = "boolean default false")
    private boolean deleted = Boolean.FALSE;

    @OneToMany
    @JoinColumn(name = "LOAN_ID")
    private List<LoanPaymentEntity> loanPayments;

    @OneToMany
    @JoinColumn(name = "LOAN_ID")
    private List<CommentEntity> comments;

    @OneToMany
    @JoinColumn(name = "LOAN_ID")
    private List<AttachedFileEntity> attachedFileEntities;

    @Column(name = "CALL_DATE")
    private Date callDate;

    @Column(name = "PROMISE_DATE")
    private Date promiseDate;

    @ManyToOne
    private LoanStatusEntity loanStatus;

    @ManyToOne
    private EmployeeEntity assignedAgent;

    @ManyToOne
    private EmployeeEntity visitor;

    @Column(name = "ATTACHED_FILE")
    private String attachedFile;

    @Column(name = "NULLIFIED", nullable = false, columnDefinition = "boolean default false")
    private boolean nullified;

    @Column(name = "nullification_request", nullable = false, columnDefinition = "boolean default false")
    private boolean nullificationRequest;

    @Column(name = "archived")
    private boolean archived;

    @Column(name = "archive_reason")
    private String archiveReason;

    @Column(name = "archive_status")
    private String archiveStatus;
}
