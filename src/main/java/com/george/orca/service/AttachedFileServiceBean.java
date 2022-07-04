package com.george.orca.service;

import com.george.orca.domain.AttachedFileEntity;
import com.george.orca.repository.AttachedFileRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachedFileServiceBean implements AttachedFileService {

    private final AttachedFileRepository attachedFileRepository;

    @Override
    public AttachedFileEntity get(Long id) {
        Optional<AttachedFileEntity> optionalAttachedFileEntity = attachedFileRepository.findById(id);
        return new TemplateUtil<AttachedFileEntity>().get(optionalAttachedFileEntity);
    }

    @Override
    public AttachedFileEntity edit(AttachedFileEntity entity) {
        return attachedFileRepository.save(entity);
    }

    @Override
    public List<AttachedFileEntity> list(Long id) {
        return attachedFileRepository.findAllByLoanId(id);
    }

    @Override
    public void delete(Long id) {
        AttachedFileEntity attachedFileEntity = get(id);
        attachedFileRepository.delete(attachedFileEntity);
    }


}
