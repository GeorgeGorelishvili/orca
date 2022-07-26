package com.george.orca.controller;

import com.george.orca.domain.OrganisationContactEntity;
import com.george.orca.domain.PersonContactEntity;
import com.george.orca.service.PersonContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("personContact")
@RequiredArgsConstructor
public class PersonContactController {

    private final PersonContactService personContactService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<PersonContactEntity> getEmployee(@RequestParam(name = "id") Long id) {
        PersonContactEntity employee = personContactService.get(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<PersonContactEntity> add(@RequestBody PersonContactEntity personContactEntity) {
        PersonContactEntity orgContact = personContactService.edit(personContactEntity);

        return ResponseEntity.ok(orgContact);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public PersonContactEntity edit(@RequestBody PersonContactEntity employeeEntity) {
        return personContactService.edit(employeeEntity);
    }

    @GetMapping("list")
    public List<PersonContactEntity> employees() {
        return personContactService.list();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam(name = "employeeId") Long employeeId) {
        personContactService.delete(employeeId);
    }
}
