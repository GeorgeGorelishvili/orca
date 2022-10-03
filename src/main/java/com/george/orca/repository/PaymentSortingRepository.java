package com.george.orca.repository;

import com.george.orca.domain.LoanPaymentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface PaymentSortingRepository extends PagingAndSortingRepository<LoanPaymentEntity, Long> {


    @Query("SELECT p, l FROM LoanPaymentEntity p " +
            "left join LoanEntity l on p.loanId = l.id WHERE" +
            "(:creditor IS NULL OR l.creditorOrganization.orgName like %:creditor%) AND " +
            "(:amountStart IS NULL OR (p.amount >:amountStart AND p.amount < :amountEnd)) AND " +
            "(:formattedDateStart IS NULL OR p.date BETWEEN :formattedDateStart AND :formattedDateEnd) AND " +
//            "(:debtor IS NULL OR (l.debtorOrganization.orgName LIKE %:debtor%) and" +
            "(:debtor IS NULL OR CONCAT(l.debtorPerson.firstname, ' ' , l.debtorPerson.lastname) LIKE %:debtor%)")
    Page<LoanPaymentEntity> getLoanPayments(Pageable paging, String creditor, String debtor, BigDecimal amountStart, BigDecimal amountEnd, Date formattedDateStart, Date formattedDateEnd);


    @Query("SELECT SUM(p.amount) FROM LoanPaymentEntity p " +
            "left join LoanEntity l on p.loanId = l.id WHERE" +
            "(:creditor IS NULL OR l.creditorOrganization.orgName like %:creditor%) AND " +
            "(:amountStart IS NULL OR (p.amount >:amountStart AND p.amount < :amountEnd)) AND " +
            "(:formattedDateStart IS NULL OR p.date BETWEEN :formattedDateStart AND :formattedDateEnd) AND " +
//            "(:debtor IS NULL OR (l.debtorOrganization.orgName LIKE %:debtor%) and" +
            "(:debtor IS NULL OR CONCAT(l.debtorPerson.firstname, ' ' , l.debtorPerson.lastname) LIKE %:debtor%)")
    List<BigDecimal> getLoanPaymentsSum(String creditor, String debtor, BigDecimal amountStart, BigDecimal amountEnd, Date formattedDateStart, Date formattedDateEnd);

}
