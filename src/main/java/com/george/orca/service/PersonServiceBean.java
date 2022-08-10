package com.george.orca.service;

import com.george.orca.domain.OrganizationEntity;
import com.george.orca.domain.PersonEntity;
import com.george.orca.repository.PersonRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<PersonEntity> page(Integer start, Integer limit, String firstname, String lastname, String personalNumber) {

        Pageable paging = PageRequest.of(start, limit);

        return personRepository.findPagedPersons(firstname, lastname, personalNumber, paging);
    }


    @Override
    public void delete(Long id) {
        PersonEntity personEntity = get(id);
        personRepository.delete(personEntity);
    }

}
