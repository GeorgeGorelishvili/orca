package com.george.orca.repository;

import com.george.orca.domain.LoanPaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanPaymentRepository extends CrudRepository<LoanPaymentEntity, Long> {
}
