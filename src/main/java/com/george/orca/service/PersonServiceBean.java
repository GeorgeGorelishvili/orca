package com.george.orca.service;

import com.george.orca.domain.PersonEntity;
import com.george.orca.repository.PersonRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PersonServiceBean implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public PersonEntity get(Long id) {
        Optional<PersonEntity> optimalPersonEntity = personRepository.findById(id);
        return new TemplateUtil<PersonEntity>().get(optimalPersonEntity);
    }

    @Override
    public PersonEntity edit(PersonEntity entity) {
        return personRepository.save(entity);
    }

    @Override
    public List<PersonEntity> list() {
        Iterable<PersonEntity> iterablePersonEntities = personRepository.findAll();
        return new TemplateUtil<PersonEntity>().list(iterablePersonEntities);
    }

    @Override
    public void delete(Long id) {
        PersonEntity personEntity = get(id);
        personRepository.delete(personEntity);
    }

}
