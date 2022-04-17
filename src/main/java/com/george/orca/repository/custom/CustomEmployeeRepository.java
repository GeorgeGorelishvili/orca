package com.george.orca.repository.custom;

import com.george.orca.domain.EmployeeEntity;

import java.util.List;

public interface CustomEmployeeRepository {

    List<EmployeeEntity> search(EmployeeEntity fields, Integer limit, Integer offset);
}
