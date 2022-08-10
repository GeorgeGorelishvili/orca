package com.george.orca.repository;

import com.george.orca.domain.OrganizationEntity;
import com.george.orca.domain.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    @Query("SELECT o FROM PersonEntity o WHERE 1=1")
    Page<PersonEntity> findPagedPersons(Pageable paging);

}
