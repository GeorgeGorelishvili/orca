package com.george.orca.repository;

import com.george.orca.domain.OrganisationContactEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganisationContactRepository extends CrudRepository<OrganisationContactEntity, Long> {

    List<OrganisationContactEntity> findAllByOrganizationId(Long id);

}
