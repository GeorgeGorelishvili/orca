package com.george.orca.service;

import com.george.orca.domain.PersonEntity;
import org.springframework.data.domain.Page;

public interface PersonService {

    PersonEntity get(Long id);

    PersonEntity edit(PersonEntity entity);

    Page<PersonEntity> page(Integer start,
                            Integer limit,
                            String firstname,
                            String lastname,
                            String personalNumber);

    void delete(Long id);

}
