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

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<OrganisationContactEntity> add(@RequestBody OrganisationContactEntity orgContactEntity) {
        OrganisationContactEntity orgContact = organisationContactService.edit(orgContactEntity);

        return ResponseEntity.ok(orgContact);
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


