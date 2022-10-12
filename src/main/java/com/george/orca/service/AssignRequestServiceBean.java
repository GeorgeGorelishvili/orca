package com.george.orca.service;

import com.george.orca.config.FileConfig;
import com.george.orca.domain.AssignRequestEntity;
import com.george.orca.domain.AssignRequestReasonsEntity;
import com.george.orca.domain.AttachedFileEntity;
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
public class AssignRequestServiceBean implements AssignRequestService {

    private final AssignRequestRepository assignRequestEntity;

    @Override
    public AssignRequestEntity edit(AssignRequestEntity entity) {
        return assignRequestEntity.save(entity);
    }
}
