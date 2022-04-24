package com.george.orca.service;

import com.george.orca.domain.LoanEntity;
import com.george.orca.domain.OrganizationEntity;
import com.george.orca.repository.OrganizationRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationServiceBean implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationEntity get(Long id) {
        Optional<OrganizationEntity> optionalOrganizationEntity = organizationRepository.findById(id);
        return new TemplateUtil<OrganizationEntity>().get(optionalOrganizationEntity);
    }

    @Override
    public OrganizationEntity edit(OrganizationEntity entity) {
        return organizationRepository.save(entity);
    }

    @Override
    public List<OrganizationEntity> list() {
        Iterable<OrganizationEntity> iterableLoanEntities = organizationRepository.findAll();
        return new TemplateUtil<OrganizationEntity>().list(iterableLoanEntities);
    }

    @Override
    public void delete(Long id) {
        OrganizationEntity organizationEntity = get(id);
        organizationRepository.delete(organizationEntity);
    }
}
