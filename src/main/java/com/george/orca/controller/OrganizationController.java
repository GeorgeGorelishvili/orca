package com.george.orca.controller;

import com.george.orca.domain.OrganizationEntity;
import com.george.orca.service.OrganizationService;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("get")
    public ResponseEntity<OrganizationEntity> get(@RequestParam(name = "organizationId") Long organizationId)  {
        OrganizationEntity organizationEntity = organizationService.get(organizationId);
        return ResponseEntity.ok(organizationEntity);
    }

    @GetMapping("add")
    public ResponseEntity<OrganizationEntity> add(
            @RequestParam(name = "loanNumber") Long loanNumber,
            @RequestParam(name = "organizationName") String organizationName,
            @RequestParam(name = "personalNumber") String personalNumber,
            @RequestParam(name = "loanAmount") BigDecimal loanAmount,
            @RequestParam(name = "responsiblePerson") String responsiblePerson,
            @RequestParam(name = "legalAddress") String legalAddress,
            @RequestParam(name = "physicalAddress") String physicalAddress) {
        OrganizationEntity organization = OrganizationEntity.builder()
                .orgName(organizationName)
                .physicalAddress(physicalAddress)
                .build();
        organization = organizationService.edit(organization);
        return ResponseEntity.ok(organization);
    }

    @PostMapping("edit")
    public ResponseEntity<OrganizationEntity> edit(@RequestBody OrganizationEntity organization) {
        organization = organizationService.edit(organization);
        return ResponseEntity.ok(organization);
    }

    @GetMapping("list")
    public List<OrganizationEntity> list() {
        Iterable<OrganizationEntity> iterableOrganizations = organizationService.list();
        return new TemplateUtil<OrganizationEntity>().list(iterableOrganizations);
    }

    @GetMapping("delete")
    public void delete(@RequestParam("organizationId") Long organizationId) {
        organizationService.delete(organizationId);
    }

}
