package com.george.orca.repository;

import com.george.orca.domain.LoanEntity;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<LoanEntity, Long> {
}
