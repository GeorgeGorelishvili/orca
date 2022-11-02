package com.george.orca.controller;

import com.george.orca.domain.OrganisationContactEntity;
import com.george.orca.domain.OrganizationEntity;
import com.george.orca.dto.LoanSearchQuery;
import com.george.orca.service.OrganisationContactService;
import com.george.orca.service.OrganizationService;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;
    private final OrganisationContactService organisationContactService;

    @GetMapping("get")
    public ResponseEntity<OrganizationEntity> get(@RequestParam(name = "organizationId") Long organizationId) {
        OrganizationEntity organizationEntity = organizationService.get(organizationId);
        return ResponseEntity.ok(organizationEntity);
    }

    @PostMapping("add")
    public ResponseEntity<OrganizationEntity> add(@RequestBody OrganizationEntity organization) {

        OrganizationEntity addedOrg = organizationService.edit(organization);
        OrganisationContactEntity organisationContactEntity = new OrganisationContactEntity()
                .builder()
                .contact(addedOrg.getOrgName())
                .phone(addedOrg.getPhoneNumber()).build();
        organisationContactService.edit(organisationContactEntity);
        return ResponseEntity.ok(addedOrg);
    }

    @PostMapping("edit")
    public ResponseEntity<OrganizationEntity> edit(@RequestBody OrganizationEntity organization) {

        List<OrganisationContactEntity> contacts = organisationContactService.list(organization.getId());
        organization.setContacts(contacts);

        organization = organizationService.edit(organization);

        return ResponseEntity.ok(organization);
    }

    @GetMapping("list")
    public ResponseEntity<Page<OrganizationEntity>> Page(Integer limit, Integer start,
                                                         @RequestParam(required = false) String orgName,
                                                         @RequestParam(required = false) String cadastrialCode) {

        Page<OrganizationEntity> orgSearchQuery = organizationService.page(start, limit, orgName, cadastrialCode);

        return ResponseEntity.ok(orgSearchQuery);
    }

    @GetMapping("delete")
    public void delete(@RequestParam("organizationId") Long organizationId) {
        organizationService.delete(organizationId);
    }

}
