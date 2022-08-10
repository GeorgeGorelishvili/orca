package com.george.orca.controller;

import com.george.orca.domain.OrganisationContactEntity;
import com.george.orca.domain.PersonContactEntity;
import com.george.orca.domain.PersonEntity;
import com.george.orca.service.PersonContactService;
import com.george.orca.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final PersonContactService personContactService;

    @GetMapping("get")
    public ResponseEntity<PersonEntity> get(@RequestParam(name = "personId") Long personId) {
        PersonEntity personEntity = personService.get(personId);
        return ResponseEntity.ok(personEntity);
    }

    @PostMapping("add")
    public ResponseEntity<PersonEntity> add(@RequestBody PersonEntity person) {

        person = personService.edit(person);
        return ResponseEntity.ok(person);
    }

    @PostMapping("edit")
    public ResponseEntity<PersonEntity> edit(@RequestBody PersonEntity person) {

        List<PersonContactEntity> contacts = personContactService.list(person.getId());
        person.setContacts(contacts);
        person = personService.edit(person);
        return ResponseEntity.ok(person);
    }

    @GetMapping("list")
    public ResponseEntity<Page<PersonEntity>> page(Integer limit, Integer start) {

        Page<PersonEntity> persSearchQuery = personService.page(start, limit);

        return ResponseEntity.ok(persSearchQuery);
    }


    @GetMapping("delete")
    public void delete(@RequestParam("personId") Long personId) {
        personService.delete(personId);
    }

}
