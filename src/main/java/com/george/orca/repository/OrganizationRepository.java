package com.george.orca.repository;

import com.george.orca.domain.OrganizationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends CrudRepository<OrganizationEntity, Long> {
}
