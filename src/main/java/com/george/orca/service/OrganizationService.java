package com.george.orca.service;

import com.george.orca.domain.OrganizationEntity;

import java.util.List;

public interface OrganizationService {

    OrganizationEntity get(Long id);
    OrganizationEntity getOrganizationByID(String code);

    OrganizationEntity edit(OrganizationEntity entity);

    List<OrganizationEntity> list();

    void delete(Long id);
}
