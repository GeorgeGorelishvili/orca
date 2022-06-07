package com.george.orca.repository;

import com.george.orca.domain.EmployeeEntity;
import com.george.orca.domain.LoanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface LoanSortingRepository extends PagingAndSortingRepository<LoanEntity, Long> {

    Page<LoanEntity> findLoanEntitiesByAssignedAgent(EmployeeEntity assignedAgent, Pageable pageable);


    //    @Query("SELECT l from LoanEntity l WHERE l.id = :id")
    Page<LoanEntity> findAll(Pageable pageable);

    @Query("SELECT l FROM LoanEntity l " +
            "left join l.creditorOrganization co ON l.creditorOrganization.id = co.id " +
            "left join l.debtorOrganization do ON l.debtorOrganization.id = do.id " +
            "left join l.debtorPerson dp ON l.debtorPerson.id = dp.id " +
            "WHERE (1=1) AND " +
            "(:localId IS NULL OR l.id = :localId) AND " +
            "(:amount IS NULL OR l.amount = :amount) AND " +
            "(:creditor IS NULL OR co.orgName LIKE %:creditor%) AND " +
            "(:debtor IS NULL OR (CONCAT(dp.firstname,dp.lastname) LIKE %:debtor% OR do.orgName LIKE %:debtor%))")
    Page<LoanEntity> findLoanEntities(Long localId, String creditor, String debtor, BigDecimal amount, Pageable paging);
}
