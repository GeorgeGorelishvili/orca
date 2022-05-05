package com.george.orca.service;

import com.george.orca.domain.LoanEntity;
import com.george.orca.domain.PersonEntity;
import com.george.orca.repository.PersonRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonService  {

    PersonEntity get(Long id);

    PersonEntity edit(PersonEntity entity);

    List<PersonEntity> list();

    void delete(Long id);

}
