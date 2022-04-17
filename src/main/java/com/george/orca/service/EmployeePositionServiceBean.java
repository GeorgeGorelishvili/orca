package com.george.orca.service;

import com.george.orca.domain.EmployeePositionEntity;
import com.george.orca.repository.EmployeePositionRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeePositionServiceBean implements EmployeePositionService {

    private final EmployeePositionRepository employeePositionRepository;

    @Override
    public EmployeePositionEntity get(Long id) {
        Optional<EmployeePositionEntity> optionalEmployeePosition = employeePositionRepository.findById(id);
        return new TemplateUtil<EmployeePositionEntity>().get(optionalEmployeePosition);
    }

    @Override
    public EmployeePositionEntity edit(EmployeePositionEntity entity) {
        return employeePositionRepository.save(entity);
    }

    @Override
    public List<EmployeePositionEntity> list() {
        Iterable<EmployeePositionEntity> iterableEmployeeEntities = employeePositionRepository.findAll();
        return new TemplateUtil<EmployeePositionEntity>().list(iterableEmployeeEntities);
    }

    @Override
    public void delete(Long employeeId) {
        EmployeePositionEntity employeePosition = get(employeeId);
        employeePositionRepository.delete(employeePosition);
    }
}
