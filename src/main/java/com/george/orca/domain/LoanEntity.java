package com.george.orca.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
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

    @Column(name = "promise_amount")
    private BigDecimal promiseAmount;

    @Column(name = "INITIAL_AMOUNT")
    private BigDecimal initialAmount;

    @Transient
    private BigDecimal fullAmount;

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

    @OneToOne
    private AssignRequestEntity assignRequest;

    @OneToMany
    @JoinColumn(name = "LOAN_ID")
    private List<LoanAgentHistoryEntity> loanAgentHistoryEntities;

    @Column(name = "paid_extra")
    private BigDecimal paidExtra;

    @OneToMany
    @JoinColumn(name = "LOAN_ID")
    private List<PromiseEntity> promises;


    public BigDecimal getFullAmount() {
        BigDecimal totalAmount = new BigDecimal(0);

        if (Objects.nonNull(loanPayments)) {
            for (LoanPaymentEntity loanPayment : loanPayments) {
                totalAmount.add(loanPayment.getAmount());
            }
        }
        return totalAmount.add(amount);
    }
}
