package com.george.orca.controller;

import com.george.orca.domain.PersonContactEntity;
import com.george.orca.domain.enums.ContactType;
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

    @GetMapping("add")
    public ResponseEntity<PersonContactEntity> add(
            @RequestParam(name = "contactType") String contactType,
            @RequestParam(name = "contact") String contact,
            @RequestParam(name = "info") String info) {
        PersonContactEntity loan = PersonContactEntity.builder()
                .contactType(ContactType.valueOf(contactType))
                .contact(contact)
                .info(info)
                .build();
        loan = personContactService.edit(loan);
        return ResponseEntity.ok(loan);
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
