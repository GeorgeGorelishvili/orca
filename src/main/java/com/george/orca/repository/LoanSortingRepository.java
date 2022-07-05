package com.george.orca.repository;

import com.george.orca.domain.EmployeeEntity;
import com.george.orca.domain.LoanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

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
            "(:nullified IS NULL OR l.nullified = :nullified) AND " +
            "(:formattedCallDateStart IS NULL OR l.callDate BETWEEN :formattedCallDateStart AND :formattedCallDateEnd) AND " +
            "(:formattedPromiseDateStart IS NULL OR l.promiseDate BETWEEN :formattedPromiseDateStart AND :formattedPromiseDateEnd) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:amount IS NULL OR l.amount = :amount) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    Page<LoanEntity> findLoanEntitiesByAssignedAgent(EmployeeEntity currentUser, Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal amount, Boolean nullified, Date formattedCallDateStart, Date formattedCallDateEnd, Date formattedPromiseDateStart, Date formattedPromiseDateEnd, String assignedAgent, Pageable paging);


    @Query("SELECT SUM(l.amount) FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "WHERE l.assignedAgent = :currentUser AND " +
            "(:nullified IS NULL OR l.nullified = :nullified) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:formattedCallDateStart IS NULL OR l.callDate BETWEEN :formattedCallDateStart AND :formattedCallDateEnd) AND " +
            "(:formattedPromiseDateStart IS NULL OR l.promiseDate BETWEEN :formattedPromiseDateStart AND :formattedPromiseDateEnd) AND " +
            "(:amount IS NULL OR l.amount = :amount) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    List<BigDecimal> getSumForAgent(EmployeeEntity currentUser, Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal amount, Boolean nullified, Date formattedCallDateStart, Date formattedCallDateEnd, Date formattedPromiseDateStart, Date formattedPromiseDateEnd, String assignedAgent);


    //    @Query("SELECT l from LoanEntity l WHERE l.id = :id")
    Page<LoanEntity> findAll(Pageable pageable);

    @Query("SELECT l FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "WHERE (1=1) AND " +
            "(:nullified IS NULL OR l.nullified = :nullified) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:formattedCallDateStart IS NULL OR l.callDate BETWEEN :formattedCallDateStart AND :formattedCallDateEnd) AND " +
            "(:formattedPromiseDateStart IS NULL OR l.promiseDate BETWEEN :formattedPromiseDateStart AND :formattedPromiseDateEnd) AND " +
            "(:amount IS NULL OR l.amount = :amount) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    Page<LoanEntity> findLoanEntities(Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal amount, Boolean nullified, Date formattedCallDateStart, Date formattedCallDateEnd, Date formattedPromiseDateStart, Date formattedPromiseDateEnd, String assignedAgent, Pageable paging);


    @Query("SELECT SUM(l.amount) FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.assignedAgent aA ON l.assignedAgent.id = aA.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "WHERE (1=1) AND " +
            "(:nullified IS NULL OR l.nullified = :nullified) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:formattedCallDateStart IS NULL OR l.callDate BETWEEN :formattedCallDateStart AND :formattedCallDateEnd) AND " +
            "(:formattedPromiseDateStart IS NULL OR l.promiseDate BETWEEN :formattedPromiseDateStart AND :formattedPromiseDateEnd) AND " +
            "(:amount IS NULL OR l.amount = :amount) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:assignedAgent IS NULL OR (CONCAT(aA.firstName,aA.lastName) LIKE %:assignedAgent%)) AND " +
            "(:debtorIdentificator IS NULL OR (dp.personalNumber LIKE %:debtorIdentificator% OR do.cadastrialCode LIKE %:debtorIdentificator%)) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    List<BigDecimal> getSum(Long localId, String creditor, String debtor, String debtorIdentificator, BigDecimal amount, Boolean nullified, Date formattedCallDateStart, Date formattedCallDateEnd, Date formattedPromiseDateStart, Date formattedPromiseDateEnd, String assignedAgent);
}


