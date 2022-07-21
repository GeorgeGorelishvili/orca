package com.george.orca.service;

import com.george.orca.domain.OrganisationContactEntity;
import com.george.orca.domain.PersonContactEntity;
import com.george.orca.repository.OrganisationContactRepository;
import com.george.orca.repository.PersonContactRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganisationContactServiceBean implements OrganisationContactService {

    private final OrganisationContactRepository organisationContactRepository;

    @Override
    public OrganisationContactEntity get(Long id) {
        Optional<OrganisationContactEntity> optionalOrganisationContactEntity = organisationContactRepository.findById(id);
        return new TemplateUtil<OrganisationContactEntity>().get(optionalOrganisationContactEntity);
    }

    @Override
    public OrganisationContactEntity edit(OrganisationContactEntity entity) {
        return organisationContactRepository.save(entity);
    }

    @Override
    public List<OrganisationContactEntity> list() {
        Iterable<OrganisationContactEntity> iterableOrganisationContactEntities = organisationContactRepository.findAll();
        return new TemplateUtil<OrganisationContactEntity>().list(iterableOrganisationContactEntities);
    }

    @Override
    public void delete(Long id) {
        OrganisationContactEntity organisationContactEntity = get(id);
        organisationContactRepository.delete(organisationContactEntity);
    }

}
