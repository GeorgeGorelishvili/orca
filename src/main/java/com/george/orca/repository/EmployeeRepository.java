package com.george.orca.repository;

import com.george.orca.domain.EmployeeEntity;
import com.george.orca.domain.LoanPaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findAllByEmployeePositionId(Long Id);


}
