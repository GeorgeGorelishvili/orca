package com.george.orca.repository;

import com.george.orca.domain.LoanEntity;
import com.george.orca.domain.LoanPaymentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanPaymentRepository extends CrudRepository<LoanPaymentEntity, Long> {

//    @Query("select l from LoanPaymentEntity l WHERE loan_id = :id ")
    List<LoanPaymentEntity> findAllByLoanId(Long id);


}
