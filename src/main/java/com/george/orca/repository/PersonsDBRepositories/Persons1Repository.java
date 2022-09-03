package com.george.orca.repository.PersonsDBRepositories;

import com.george.orca.domain.OrganizationEntity;
import com.george.orca.domain.personEntities.Persons1Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Persons1Repository extends CrudRepository<Persons1Entity, Long> {

    //    @Query("SELECT o FROM OrganizationEntity o WHERE 1=1 AND " +
//            "(:orgName IS NULL OR o.orgName  LIKE %:orgName%) AND " +
//            "(:cadastrialCode IS NULL OR o.cadastrialCode  LIKE %:cadastrialCode%)")
    List<Persons1Entity> findPersons1EntitiesByPersonalNumberLike(String personalNumber);

}
