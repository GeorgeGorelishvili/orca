package com.george.orca.service;

import com.george.orca.domain.EmployeeEntity;
import com.george.orca.repository.EmployeeRepository;
import com.george.orca.repository.custom.CustomEmployeeRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceBean implements EmployeeService {

    private final CustomEmployeeRepository  customEmployeeRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeEntity> find(EmployeeEntity employee, Integer limit, Integer offset) {
        return customEmployeeRepository.search(employee, limit, offset);
    }

    @Override
    public EmployeeEntity get(Long id) {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);
        return new TemplateUtil<EmployeeEntity>().get(optionalEmployeeEntity);
    }

    @Override
    public EmployeeEntity edit(EmployeeEntity entity) {
        return employeeRepository.save(entity);
    }

    @Override
    public List<EmployeeEntity> list() {
        Iterable<EmployeeEntity> iterableEmployeeEntities = employeeRepository.findAll();
        return new TemplateUtil<EmployeeEntity>().list(iterableEmployeeEntities);
    }

    public List<EmployeeEntity> getAgents() {
        Long agentId = Long.valueOf(1);
        Iterable<EmployeeEntity> iterableEmployeeEntities = employeeRepository.findAllByEmployeePositionId(agentId);
        return new TemplateUtil<EmployeeEntity>().list(iterableEmployeeEntities);
    }

    public List<EmployeeEntity> getVisitors() {
        Long agentId = Long.valueOf(3);
        Iterable<EmployeeEntity> iterableEmployeeEntities = employeeRepository.findAllByEmployeePositionId(agentId);
        return new TemplateUtil<EmployeeEntity>().list(iterableEmployeeEntities);
    }


    @Override
    public void delete(Long employeeId) {
        EmployeeEntity employeeEntity = get(employeeId);
        employeeRepository.delete(employeeEntity);
    }
}
