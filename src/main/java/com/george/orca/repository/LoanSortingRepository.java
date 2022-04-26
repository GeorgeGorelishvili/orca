package com.george.orca.repository;

import com.george.orca.domain.LoanEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface LoanSortingRepository extends PagingAndSortingRepository<LoanEntity, Long> {

    @Query("select l from LoanEntity l WHERE l.amount  =:amount")
    List<LoanEntity> loanWithPaging(@Param("amount") BigDecimal amount, Pageable pageable);
}
