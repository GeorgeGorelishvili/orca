package com.george.orca.repository;

import com.george.orca.domain.EmployeeEntity;
import com.george.orca.domain.LoanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LoanSortingRepository extends PagingAndSortingRepository<LoanEntity, Long> {

    Page<LoanEntity> findLoanEntitiesByAssignedAgent(EmployeeEntity assignedAgent, Pageable pageable);


    @Query("select l from LoanEntity l WHERE 1=1")
    Page<LoanEntity> loanWithPaging(Pageable pageable);
}
