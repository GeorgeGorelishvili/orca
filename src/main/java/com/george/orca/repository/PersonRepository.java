package com.george.orca.repository;

import com.george.orca.domain.OrganizationEntity;
import com.george.orca.domain.PersonEntity;
import com.george.orca.domain.personEntities.Persons1Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    @Query("SELECT p FROM PersonEntity p WHERE 1=1 AND " +
            "(:firstname IS NULL OR p.firstname  LIKE %:firstname%) AND " +
            "(:lastname IS NULL OR p.lastname  LIKE %:lastname%) AND " +
            "(:personalNumber IS NULL OR p.personalNumber  LIKE %:personalNumber%)")
    Page<PersonEntity> findPagedPersons(String firstname, String lastname, String personalNumber, Pageable paging);

    Optional<PersonEntity> findByPersonalNumberLike(String personalNumber);


}
