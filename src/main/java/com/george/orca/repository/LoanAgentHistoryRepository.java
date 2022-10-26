package com.george.orca.repository;

import com.george.orca.domain.EmployeeEntity;
import com.george.orca.domain.LoanAgentHistoryEntity;
import com.george.orca.domain.LoanPaymentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanAgentHistoryRepository extends CrudRepository<LoanAgentHistoryEntity, Long> {

    @Query("select l from LoanAgentHistoryEntity l WHERE l.loanId = :id")
    List<LoanAgentHistoryEntity> findAllByLoanId(Long id);


}
