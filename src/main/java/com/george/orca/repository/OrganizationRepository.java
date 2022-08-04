package com.george.orca.repository;

import com.george.orca.domain.OrganizationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends CrudRepository<OrganizationEntity, Long> {

    Optional<OrganizationEntity> findOrganizationEntityByCadastrialCode(String code);


}
