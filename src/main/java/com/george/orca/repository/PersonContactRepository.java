package com.george.orca.repository;

import com.george.orca.domain.PersonContactEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonContactRepository extends CrudRepository<PersonContactEntity, Long> {
}
