package com.george.orca.repository;

import com.george.orca.domain.OrganisationContactEntity;
import com.george.orca.domain.PersonContactEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonContactRepository extends CrudRepository<PersonContactEntity, Long> {
    List<PersonContactEntity> findAllByPersonId(Long id);

}
