package com.george.orca.repository;

import com.george.orca.domain.EmployeeEntity;
import com.george.orca.domain.LoanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface LoanSortingRepository extends PagingAndSortingRepository<LoanEntity, Long> {

    @Query("SELECT l FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "WHERE l.assignedAgent = :currentUser AND " +
            "l.nullified =:nullified AND " +
            "l.nullificationRequest =:nullificationRequest AND " +
            "l.archived =:archived AND " +
            "(:formattedCallDateStart IS NULL OR l.callDate BETWEEN :formattedCallDateStart AND :formattedCallDateEnd) AND " +
            "(:formattedPromiseDateStart IS NULL OR l.promiseDate BETWEEN :formattedPromiseDateStart AND :formattedPromiseDateEnd) AND " +
            "(:amountStart IS NULL OR l.amount > :amountStart) AND " +
            "(:amountEnd IS NULL OR l.amount < :amountEnd) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:amount IS NULL OR l.amount = :amount) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    Page<LoanEntity> findLoanEntitiesByAssignedAgent(EmployeeEntity currentUser, Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal amount, BigDecimal amountStart, BigDecimal amountEnd, Boolean nullified, Date formattedCallDateStart, Date formattedCallDateEnd, Date formattedPromiseDateStart, Date formattedPromiseDateEnd, String assignedAgent, Boolean nullificationRequest, Boolean archived, Pageable paging);


    @Query("SELECT SUM(l.amount) FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "WHERE l.assignedAgent = :currentUser AND " +
            "l.nullified =:nullified AND " +
            "l.nullificationRequest =:nullificationRequest AND " +
            "l.archived =:archived AND " +
            "(:amountStart IS NULL OR l.amount > :amountStart) AND " +
            "(:amountEnd IS NULL OR l.amount < :amountEnd) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:formattedCallDateStart IS NULL OR l.callDate BETWEEN :formattedCallDateStart AND :formattedCallDateEnd) AND " +
            "(:formattedPromiseDateStart IS NULL OR l.promiseDate BETWEEN :formattedPromiseDateStart AND :formattedPromiseDateEnd) AND " +
            "(:amount IS NULL OR l.amount = :amount) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    List<BigDecimal> getSumForAgent(EmployeeEntity currentUser, Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal amount, BigDecimal amountStart, BigDecimal amountEnd, Boolean nullified, Date formattedCallDateStart, Date formattedCallDateEnd, Date formattedPromiseDateStart, Date formattedPromiseDateEnd, String assignedAgent, Boolean nullificationRequest, Boolean archived);


    //    @Query("SELECT l from LoanEntity l WHERE l.id = :id")
    Page<LoanEntity> findAll(Pageable pageable);

    @Query("SELECT l FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "WHERE (1=1) AND " +
            "l.nullified = :nullified AND l.archived = :archived AND " +
            "l.nullificationRequest = :nullificationRequest AND " +
            "(:amountStart IS NULL OR l.amount > :amountStart) AND " +
            "(:amountEnd IS NULL OR l.amount < :amountEnd) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:formattedCallDateStart IS NULL OR l.callDate BETWEEN :formattedCallDateStart AND :formattedCallDateEnd) AND " +
            "(:formattedPromiseDateStart IS NULL OR l.promiseDate BETWEEN :formattedPromiseDateStart AND :formattedPromiseDateEnd) AND " +
            "(:amount IS NULL OR l.amount = :amount) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    Page<LoanEntity> findLoanEntities(Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal amount, BigDecimal amountStart, BigDecimal amountEnd, Boolean nullified, Date formattedCallDateStart, Date formattedCallDateEnd, Date formattedPromiseDateStart, Date formattedPromiseDateEnd, String assignedAgent, Boolean nullificationRequest, Boolean archived, Pageable paging);


    @Query("SELECT SUM(l.amount) FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "WHERE (1=1) AND " +
            "l.nullified = :nullified AND l.archived = :archived AND " +
            "l.nullificationRequest = :nullificationRequest AND " +
            "(:amountStart IS NULL OR l.amount > :amountStart) AND " +
            "(:amountEnd IS NULL OR l.amount < :amountEnd) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:formattedCallDateStart IS NULL OR l.callDate BETWEEN :formattedCallDateStart AND :formattedCallDateEnd) AND " +
            "(:formattedPromiseDateStart IS NULL OR l.promiseDate BETWEEN :formattedPromiseDateStart AND :formattedPromiseDateEnd) AND " +
            "(:amount IS NULL OR l.amount = :amount) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    List<BigDecimal> getSum(Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal amount, BigDecimal amountStart, BigDecimal amountEnd, Boolean nullified, Date formattedCallDateStart, Date formattedCallDateEnd, Date formattedPromiseDateStart, Date formattedPromiseDateEnd, String assignedAgent, Boolean nullificationRequest, Boolean archived);

    @Query("SELECT l FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id WHERE " +
            "l.assignRequest IS NULL AND " +
            "(l.nullified = :nullified OR l.archived = :archived) AND " +
            "(l.nullificationRequest = :nullificationRequest) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:status IS NULL OR l.archiveStatus = :status) AND " +
            "(:formattedCallDateStart IS NULL OR l.callDate BETWEEN :formattedCallDateStart AND :formattedCallDateEnd) AND " +
            "(:formattedPromiseDateStart IS NULL OR l.promiseDate BETWEEN :formattedPromiseDateStart AND :formattedPromiseDateEnd) AND " +
            "(:amount IS NULL OR l.amount = :amount) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    Page<LoanEntity> findArchiveLoanEntities(Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal amount, Boolean nullified, Date formattedCallDateStart, Date formattedCallDateEnd, Date formattedPromiseDateStart, Date formattedPromiseDateEnd, String assignedAgent, Boolean nullificationRequest, Boolean archived, String status, Pageable paging);


    @Query("SELECT SUM(l.amount) FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id WHERE " +
            "(l.assignRequest IS NULL) AND " +
            "(l.nullified = :nullified OR l.archived = :archived) AND " +
            "(l.nullificationRequest = :nullificationRequest) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:status IS NULL OR l.archiveStatus = :status) AND " +
            "(:formattedCallDateStart IS NULL OR l.callDate BETWEEN :formattedCallDateStart AND :formattedCallDateEnd) AND " +
            "(:formattedPromiseDateStart IS NULL OR l.promiseDate BETWEEN :formattedPromiseDateStart AND :formattedPromiseDateEnd) AND " +
            "(:amount IS NULL OR l.amount = :amount) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    List<BigDecimal> getArchiveSum(Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal amount, Boolean nullified, Date formattedCallDateStart, Date formattedCallDateEnd, Date formattedPromiseDateStart, Date formattedPromiseDateEnd, String assignedAgent, Boolean nullificationRequest, Boolean archived, String status);


    @Query("SELECT DISTINCT dp, do, co, size(lp), l.assignedAgent, l FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left join l.loanPayments lp ON l.id = lp.loanId " +
            "WHERE " +
            "size(l.loanPayments) > 0 AND " +
            "(:withCheck IS NULL OR lp.withCheck = :withCheck) AND " +
            "(:formattedDateStart IS NULL OR lp.date BETWEEN :formattedDateStart AND :formattedDateEnd) AND " +
            "(:convertedAmountStart IS NULL OR (lp.amount >:convertedAmountStart AND lp.amount < :convertedAmountEnd)) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    Page<LoanEntity> findEntitiesWithPayments(Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal convertedAmountStart, BigDecimal convertedAmountEnd, Date formattedDateStart, Date formattedDateEnd, String assignedAgent, Boolean withCheck, Pageable paging);

    @Query("SELECT SUM(lp.amount) FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left join l.loanPayments lp ON l.id = lp.loanId " +
            "WHERE " +
            "size(l.loanPayments) > 0 AND " +
            "(:withCheck IS NULL OR lp.withCheck = :withCheck) AND " +
            "(:formattedDateStart IS NULL OR lp.date BETWEEN :formattedDateStart AND :formattedDateEnd) AND " +
            "(:convertedAmountStart IS NULL OR (lp.amount >:convertedAmountStart AND lp.amount < :convertedAmountEnd)) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    List<BigDecimal> findEntitiesWithPaymentsSum(Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal convertedAmountStart, BigDecimal convertedAmountEnd, Date formattedDateStart, Date formattedDateEnd, String assignedAgent, Boolean withCheck);

    @Query("SELECT count(lp.size) FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left join l.loanPayments lp ON l.id = lp.loanId " +
            "WHERE " +
            "size(l.loanPayments) > 0 AND " +
            "(:withCheck IS NULL OR lp.withCheck = :withCheck) AND " +
            "(:formattedDateStart IS NULL OR lp.date BETWEEN :formattedDateStart AND :formattedDateEnd) AND " +
            "(:convertedAmountStart IS NULL OR (lp.amount >:convertedAmountStart AND lp.amount < :convertedAmountEnd)) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    List<BigDecimal> findEntitiesWithPaymentsCount(Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal convertedAmountStart, BigDecimal convertedAmountEnd, Date formattedDateStart, Date formattedDateEnd, String assignedAgent, Boolean withCheck);

    @Query("SELECT sum(lp.amount) FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left join l.loanPayments lp ON l.id = lp.loanId " +
            "WHERE " +
            "size(l.loanPayments) > 0 AND " +
            "(lp.withCheck is true) AND " +
            "(:withCheck IS NULL OR lp.withCheck = :withCheck) AND " +
            "(:formattedDateStart IS NULL OR lp.date BETWEEN :formattedDateStart AND :formattedDateEnd) AND " +
            "(:convertedAmountStart IS NULL OR (lp.amount >:convertedAmountStart AND lp.amount < :convertedAmountEnd)) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    List<BigDecimal> findEntitiesWithPaymentsWithCheckAmount(Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal convertedAmountStart, BigDecimal convertedAmountEnd, Date formattedDateStart, Date formattedDateEnd, String assignedAgent, Boolean withCheck);


    @Query("SELECT SUM(l.paidExtra) from LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left outer join l.loanPayments lp ON l.id = lp.loanId " +
            "WHERE " +
            "size(l.loanPayments) > 0 AND " +
            "(:withCheck IS NULL OR lp.withCheck = :withCheck) AND " +
            "(:formattedDateStart IS NULL OR lp.date BETWEEN :formattedDateStart AND :formattedDateEnd) AND " +
            "(:convertedAmountStart IS NULL OR (lp.amount >:convertedAmountStart AND lp.amount < :convertedAmountEnd)) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    List<BigDecimal> findPaidExtra(Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal convertedAmountStart, BigDecimal convertedAmountEnd, Date formattedDateStart, Date formattedDateEnd, String assignedAgent, Boolean withCheck);


    //assignRequests ===========================================================================================

    @Query("SELECT l FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left join l.assignRequest aR on l.assignRequest.id = aR.id  " +
            "left join aR.reason r on aR.reason.id = r.id " +
            "WHERE (1=1) AND " +
            "(r.id = :assignRequestReasonId) AND " +
            "l.nullified = :nullified AND l.archived = :archived AND " +
            "l.nullificationRequest = :nullificationRequest AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:employeeId IS NULL OR l.assignedAgent.id = :employeeId) AND " +
            "(:amount IS NULL OR l.amount = :amount) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    Page<LoanEntity> getAssignRequestLoans(Long localId, Long employeeId, Long assignRequestReasonId, String creditor, String debtor, String debtorIdentificator, BigDecimal amount, Boolean nullified, String assignedAgent, Boolean nullificationRequest, Boolean archived, Pageable paging);


    @Query("SELECT SUM(l.amount) FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left join l.assignRequest aR on l.assignRequest.id = aR.id  " +
            "left join aR.reason r on aR.reason.id = r.id " +
            "WHERE (1=1) AND " +
            "r.id = 1 AND " +
            "l.assignRequest.reason.id = :assignRequestReasonId AND " +
            "l.nullified = :nullified AND l.archived = :archived AND " +
            "l.nullificationRequest = :nullificationRequest AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:employeeId IS NULL OR l.assignedAgent.id = :employeeId) AND " +
            "(:amount IS NULL OR l.amount = :amount) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    List<BigDecimal> getSumForAssignRequestLoans(Long localId, Long employeeId, Long assignRequestReasonId, String creditor, String debtor, String debtorIdentificator, BigDecimal amount, Boolean nullified, String assignedAgent, Boolean nullificationRequest, Boolean archived);


    //---------------------------------------------------------------------- Promise Grid

    @Query("SELECT DISTINCT dp, do, co, size(lp), l.assignedAgent, l FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left join l.loanPayments lp ON l.id = lp.loanId " +
            "left join l.comments c ON l.id = c.loanId " +
            "WHERE " +
            "(l.nullificationRequest = false) AND " +
            "(l.promiseDate is not null) AND " +
            "(l.archived = false) AND " +
            "(l.nullified = false) AND " +
            "(:formattedDateStart IS NULL OR lp.date BETWEEN :formattedDateStart AND :formattedDateEnd) AND " +
            "(:convertedAmountStart IS NULL OR (c.value >:convertedAmountStart AND c.value < :convertedAmountEnd)) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:employeeId IS NULL OR l.assignedAgent.id = :employeeId) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    Page<LoanEntity> findLoanPromises(Long localId, Long employeeId, String creditor, String debtor, String debtorIdentificator, BigDecimal convertedAmountStart, BigDecimal convertedAmountEnd, Date formattedDateStart, Date formattedDateEnd, String assignedAgent, Pageable paging);


    @Query("SELECT sum(l.promiseAmount) FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left join l.loanPayments lp ON l.id = lp.loanId " +
            "WHERE " +
            "(l.nullificationRequest = false) AND " +
            "(l.promiseDate is not null) AND " +
            "(l.archived = false) AND " +
            "(l.nullified = false) AND " +
            "(:employeeId IS NULL OR l.assignedAgent.id = :employeeId) AND " +
            "(:formattedDateStart IS NULL OR lp.date BETWEEN :formattedDateStart AND :formattedDateEnd) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    List<BigDecimal> loanPromisesSum(Long localId, Long employeeId, String creditor, String debtor, String debtorIdentificator, Date formattedDateStart, Date formattedDateEnd, String assignedAgent);

    @Query("SELECT DISTINCT sum(l.amount) FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left join l.loanPayments lp ON l.id = lp.loanId " +
            "WHERE " +
            "(l.promiseDate is not null) AND " +
            "(l.nullificationRequest = false) AND " +
            "(l.archived = false) AND " +
            "(l.nullified = false) AND " +
            "(:employeeId IS NULL OR l.assignedAgent.id = :employeeId) AND " +
            "(:formattedDateStart IS NULL OR lp.date BETWEEN :formattedDateStart AND :formattedDateEnd) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    List<BigDecimal> loanTotalAmountSum(Long localId, Long employeeId, String creditor, String debtor, String debtorIdentificator, Date formattedDateStart, Date formattedDateEnd, String assignedAgent);


    @Query("SELECT DISTINCT dp, do, co, size(lp), l.assignedAgent, l FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left join l.loanPayments lp ON l.id = lp.loanId " +
            "WHERE " +
            "(l.nullificationRequest = false) AND " +
            "(l.archived = false) AND " +
            "(l.nullified = false) AND " +
            "(l.callDate is null) AND " +
            "(:formattedDateStart IS NULL OR lp.date BETWEEN :formattedDateStart AND :formattedDateEnd) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:employeeId IS NULL OR l.assignedAgent.id = :employeeId) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    Page<LoanEntity> findLoansWithoutCallDate(Long localId, Long employeeId, String creditor, String debtor, String debtorIdentificator,  Date formattedDateStart, Date formattedDateEnd, String assignedAgent, Pageable paging);


    @Query("SELECT DISTINCT sum(l.amount) FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left join l.loanPayments lp ON l.id = lp.loanId " +
            "WHERE " +
            "(l.nullificationRequest = false) AND " +
            "(l.archived = false) AND " +
            "(l.nullified = false) AND " +
            "(l.callDate is null) AND " +
            "(:employeeId IS NULL OR l.assignedAgent.id = :employeeId) AND " +
            "(:formattedDateStart IS NULL OR lp.date BETWEEN :formattedDateStart AND :formattedDateEnd) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    List<BigDecimal> findLoansWithoutCallDateSum(Long localId, Long employeeId, String creditor, String debtor, String debtorIdentificator, Date formattedDateStart, Date formattedDateEnd, String assignedAgent);



}


