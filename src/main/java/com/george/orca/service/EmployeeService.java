package com.george.orca.service;

import com.george.orca.domain.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    EmployeeEntity get(Long id);

    EmployeeEntity edit(EmployeeEntity entity);

    List<EmployeeEntity> list();

    List<EmployeeEntity> getAgents();

    List<EmployeeEntity> getVisitors();


    void delete(Long id);

    List<EmployeeEntity> find(EmployeeEntity employee, Integer limit, Integer offset);

}
