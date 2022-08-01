package com.george.orca.controller;

import com.george.orca.domain.PersonEntity;
import com.george.orca.service.PersonService;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("get")
    public ResponseEntity<PersonEntity> get(@RequestParam(name = "personId") Long personId)  {
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
        person = personService.edit(person);
        return ResponseEntity.ok(person);
    }

    @GetMapping("list")
    public List<PersonEntity> list() {
        Iterable<PersonEntity> personEntityIterable = personService.list();
        return new TemplateUtil<PersonEntity>().list(personEntityIterable);
    }

    @GetMapping("delete")
    public void delete(@RequestParam("personId") Long personId) {
        personService.delete(personId);
    }

}
