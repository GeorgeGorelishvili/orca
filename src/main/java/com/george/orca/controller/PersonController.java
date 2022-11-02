package com.george.orca.controller;

import com.george.orca.domain.OrganisationContactEntity;
import com.george.orca.domain.PersonContactEntity;
import com.george.orca.domain.PersonEntity;
import com.george.orca.domain.personEntities.Persons1Entity;
import com.george.orca.repository.PersonsDBRepositories.Persons1Repository;
import com.george.orca.service.PersonContactService;
import com.george.orca.service.PersonService;
import com.george.orca.service.PersonsDBServices.Persons1Service;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final Persons1Service persons1Service;
    private final PersonContactService personContactService;

    @GetMapping("get")
    public ResponseEntity<PersonEntity> get(@RequestParam(name = "personId") Long personId) {
        PersonEntity personEntity = personService.get(personId);
        return ResponseEntity.ok(personEntity);
    }

    @PostMapping("add")
    public ResponseEntity<String> add(@RequestBody PersonEntity person) {

        PersonEntity alreadyExistsPerson = personService.search(person.getPersonalNumber());

        if (alreadyExistsPerson == null) {
            person = personService.edit(person);
            PersonContactEntity personAsContact = new PersonContactEntity().builder()
                    .contact(person.getFirstname() + "  " + person.getLastname()).phone(person.getPhone()).build();

            personContactService.edit(personAsContact);

            for (Persons1Entity match : persons1Service.search(person.getPersonalNumber())) {
                PersonContactEntity personContact = PersonContactEntity.builder()
                        .contact(match.getFirstname() + " " + match.getLastname())
                        .personId(person.getId())
                        .phone(match.getPhone())
                        .contactInfo("დაბ წელი: " + match.getBirthYear())
                        .build();
                personContactService.edit(personContact);
            }


            return ResponseEntity.status(HttpStatus.OK).body("წარმატებით დაემატა");
        } else {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("პერსონა უკვე ბაზაშია");
        }

    }

    @PostMapping("edit")
    public ResponseEntity<PersonEntity> edit(@RequestBody PersonEntity person) {

        List<PersonContactEntity> contacts = personContactService.list(person.getId());
        person.setContacts(contacts);
        person = personService.edit(person);
        return ResponseEntity.ok(person);
    }

    @GetMapping("list")
    public ResponseEntity<Page<PersonEntity>> page(Integer limit, Integer start,
                                                   @RequestParam(required = false) String firstname,
                                                   @RequestParam(required = false) String lastname,
                                                   @RequestParam(required = false) String personalNumber) {

        Page<PersonEntity> persSearchQuery = personService.page(start, limit, firstname, lastname, personalNumber);

        return ResponseEntity.ok(persSearchQuery);
    }


    @GetMapping("delete")
    public void delete(@RequestParam("personId") Long personId) {
        personService.delete(personId);
    }

}
