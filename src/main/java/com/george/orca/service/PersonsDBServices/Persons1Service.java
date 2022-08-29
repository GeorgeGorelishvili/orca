package com.george.orca.service.PersonsDBServices;

import com.george.orca.domain.CommentEntity;
import com.george.orca.domain.personEntities.Persons1Entity;

import java.util.List;

public interface Persons1Service {

    Persons1Entity get(Long id);


    Persons1Entity edit(Persons1Entity entity);

    List<Persons1Entity> list();

    void delete(Long id);
}
