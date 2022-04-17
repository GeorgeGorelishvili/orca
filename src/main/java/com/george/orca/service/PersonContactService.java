package com.george.orca.service;

import com.george.orca.domain.PersonContactEntity;

import java.util.List;

public interface PersonContactService {

    PersonContactEntity get(Long id);

    PersonContactEntity edit(PersonContactEntity entity);

    List<PersonContactEntity> list();

    void delete(Long id);
}
