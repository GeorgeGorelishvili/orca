package com.george.orca.service;

import com.george.orca.domain.OrganisationContactEntity;

import java.util.List;

public interface OrganisationContactService {
    OrganisationContactEntity get(Long id);

    OrganisationContactEntity edit(OrganisationContactEntity entity);

    List<OrganisationContactEntity> list();

    void delete(Long id);
}
