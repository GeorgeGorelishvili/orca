package com.george.orca.controller;

import com.george.orca.domain.OrganisationContactEntity;
import com.george.orca.domain.PersonContactEntity;
import com.george.orca.repository.OrganisationContactRepository;
import com.george.orca.service.OrganisationContactService;
import com.george.orca.service.PersonContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("organisationContact")
@RequiredArgsConstructor
public class OrganisationContactController {

    private final OrganisationContactService organisationContactService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<OrganisationContactEntity> getEmployee(@RequestParam(name = "id") Long id) {
        OrganisationContactEntity employee = organisationContactService.get(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("add")
    public ResponseEntity<OrganisationContactEntity> add(
            @RequestParam(name = "contactType") String contactType,
            @RequestParam(name = "contact") String contact) {
        OrganisationContactEntity loan = OrganisationContactEntity.builder()
                .contact(contact)
                .contactType(contactType)
                .build();
        loan = organisationContactService.edit(loan);
        return ResponseEntity.ok(loan);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public OrganisationContactEntity edit(@RequestBody OrganisationContactEntity employeeEntity) {
        return organisationContactService.edit(employeeEntity);
    }

    @GetMapping("list")
    public List<OrganisationContactEntity> employees() {
        return organisationContactService.list();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam(name = "employeeId") Long employeeId) {
        organisationContactService.delete(employeeId);
    }
}


