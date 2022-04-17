package com.george.orca.service;

import com.george.orca.domain.EmployeePositionEntity;

import java.util.List;

public interface EmployeePositionService {

    EmployeePositionEntity get(Long id);

    EmployeePositionEntity edit(EmployeePositionEntity entity);

    List<EmployeePositionEntity> list();

    void delete(Long id);

}
