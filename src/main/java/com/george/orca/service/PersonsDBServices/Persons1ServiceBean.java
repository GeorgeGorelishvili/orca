package com.george.orca.service.PersonsDBServices;

import com.george.orca.domain.personEntities.Persons1Entity;
import com.george.orca.repository.PersonsDBRepositories.Persons1Repository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Persons1ServiceBean implements Persons1Service {

    private final Persons1Repository persons1Repository;


    @Override
    public Persons1Entity get(Long id) {
        Optional<Persons1Entity> optionalPersons1Entity = persons1Repository.findById(id);
        return new TemplateUtil<Persons1Entity>().get(optionalPersons1Entity);
    }

    @Override
    public Persons1Entity edit(Persons1Entity entity) {
        return persons1Repository.save(entity);
    }

    @Override
    public List<Persons1Entity> search(String personalNumber) {

        return persons1Repository.findPersons1EntitiesByPersonalNumberLike(personalNumber);
    }

    @Override
    public List<Persons1Entity> list() {
        return new TemplateUtil<Persons1Entity>().list(persons1Repository.findAll());
    }

    @Override
    public void delete(Long id) {
        Persons1Entity entity = get(id);
        persons1Repository.delete(entity);
    }
}
