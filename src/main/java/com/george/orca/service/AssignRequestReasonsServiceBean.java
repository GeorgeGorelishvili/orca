package com.george.orca.service;

import com.george.orca.config.FileConfig;
import com.george.orca.domain.AssignRequestEntity;
import com.george.orca.domain.AssignRequestReasonsEntity;
import com.george.orca.domain.AttachedFileEntity;
import com.george.orca.domain.EmployeeEntity;
import com.george.orca.repository.AssignRequestReasonsRepository;
import com.george.orca.repository.AssignRequestRepository;
import com.george.orca.repository.AttachedFileRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssignRequestReasonsServiceBean implements AssignRequestReasonsService {

    private final AssignRequestReasonsRepository assignRequestReasonsRepository;


    @Override
    public AssignRequestReasonsEntity get(Long id) {

        Optional<AssignRequestReasonsEntity> assignRequestReasonsEntityOptional = assignRequestReasonsRepository.findById(id);

        return new TemplateUtil<AssignRequestReasonsEntity>().get(assignRequestReasonsEntityOptional);
    }
}
