package com.george.orca.repository;

import com.george.orca.domain.EmployeePositionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePositionRepository extends CrudRepository<EmployeePositionEntity, Long> {
}
