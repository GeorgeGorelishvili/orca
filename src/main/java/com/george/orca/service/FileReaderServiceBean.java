package com.george.orca.service;

import com.george.orca.config.FileConfig;
import com.george.orca.controller.OrganizationController;
import com.george.orca.domain.*;
import com.george.orca.domain.personEntities.Persons1Entity;
import com.george.orca.dto.ExcelRowDTO;
import com.george.orca.repository.OrganizationRepository;
import com.george.orca.repository.PersonRepository;
import com.george.orca.service.PersonsDBServices.Persons1Service;
import com.george.orca.utils.ExcelParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileReaderServiceBean implements FileReaderService {
    private final PersonService personService;
    private final PersonContactService personContactService;
    private final OrganisationContactService organizationContactService;
    private final OrganizationService organizationService;
    private final EmployeeService employeeService;
    private final LoanPaymentService loanPaymentService;

    private final Persons1Service personsDB1;

    private final EntityManager entitymanager;

    private final LoanService loanService;

    private final PersonRepository personRepository;
    private final OrganizationRepository organizationRepository;

    private final FileConfig fileConfig;

    @Override
    public File readFile(String filename) {

        String filePath = fileConfig.getFolderPath();
        if (!filePath.endsWith("/")) {
            filePath += "/";
        }
        filePath += filename;
        log.info("reading file: " + filePath);

        File file = new File(filePath);

        IOUtils.setByteArrayMaxOverride(245304546);

        ExcelParser excelParser = new ExcelParser(file);

        Integer counter = 0;
        List<ExcelRowDTO> rowData = excelParser.getPersons();

        for (ExcelRowDTO record : rowData) {

//
//            Persons1Entity persona = new Persons1Entity().builder()
//                    .firstname(record.getName())
//                    .lastname(record.getLastname())
//                    .birthYear(record.getBirthYear())
//                    .personalNumber(record.getPersonalNumber())
//                    .phone(record.getPhone())
//                    .physicalAddress(record.getPhysicalAddress())
//                    .build();
//            personsDB1.edit(persona);
//
//
//            PersonEntity person = personService.search(record.getIdCode());
//            if (person != null) {
//                log.info("we have a match !! : " + record.getIdCode());
//                PersonContactEntity personContact = PersonContactEntity.builder()
//                        .contact(record.getFirstname() + " " + record.getLastname() + " პ/ნ : " + record.getIdCode())
//                        .personId(person.getId())
//                        .phone(record.getPhone())
//                        .physicalAddress(record.getPhysicalAddress())
//                        .contactInfo(record.getBirthYear())
//                        .build();
//
//                personContactService.edit(personContact);
//            }
//            counter++;

//            log.info("current: " + counter);


//            LoanEntity loan = loanService.get(record.getLoanId());

//            if (loan != null) {
//                if (loan.getDebtorOrganization() != null) {
//                    OrganisationContactEntity orgContact = OrganisationContactEntity.builder()
//                            .contact(record.getContact())
//                            .organizationId(loan.getDebtorOrganization().getId())
//                            .build();
//                    organisationContactService.edit(orgContact);
//                } else if (loan.getDebtorPerson() != null) {
//                    PersonContactEntity personContact = PersonContactEntity.builder()
//                            .contact(record.getContact())
//                            .personId(loan.getDebtorPerson().getId())
//                            .build();
//                    personContactService.edit(personContact);
//                }
//            }else{
//                log.info("loan: "+ record.getLoanId());
//
//            }
//
//

            OrganizationEntity orgEntity = new OrganizationEntity();
            orgEntity.setPhysicalAddress(record.getPhysicalAddress());
            orgEntity.setLegalAddress(record.getLegalAddress());
            orgEntity.setDirector(record.getContact());
            orgEntity.setCadastrialCode(record.getIdCode());
            orgEntity.setPhoneNumber(record.getPhone());

            organizationService.edit(orgEntity);

            organizationRepository.save(orgEntity);

//            Long personId = personEntity.getId();
//
//            OrganizationEntity creditor = organizationService.get(record.getCreditorOrganizationId());
//
//            loanEntity.setCreditorOrganization(creditor);
//

//
//debtor
            long creditorOrganization = 7;
            LoanEntity loanEntity = new LoanEntity();


            loanEntity.setAmount(record.getAmount());
            loanEntity.setInitialAmount(record.getAmount());
            loanEntity.setIncomeDate(record.getIncomeDate());
            loanEntity.setStartDate(record.getStartDate());

            PersonEntity person = personService.search(record.getIdCode());
            if (person != null) {
                log.info("we have a match !! : " + record.getIdCode());
                loanEntity.setDebtorPerson(person);
            } else {
                PersonEntity debtor = new PersonEntity().builder()
                        .personalNumber(record.getIdCode())
                        .firstname(record.getFirstname())
                        .lastname(record.getLastname())
                        .legalAddress(record.getLegalAddress())
                        .physicalAddress(record.getPhysicalAddress())
                        .phone(record.getPhone())
                        .build();
                debtor = personService.edit(debtor);
                loanEntity.setDebtorPerson(debtor);
            }


            OrganizationEntity creditorOrg;
            creditorOrg = organizationService.get(creditorOrganization);

            loanEntity.setCreditorOrganization(creditorOrg);
            loanEntity.setDebtorPerson(person);
//            loanEntity.setDebtorOrganization(orgEntity);

//            loanEntity.setId(record.getLoanId());
            loanEntity.setStartDate(record.getStartDate());
            loanEntity.setIncomeDate(record.getIncomeDate());

            loanService.edit(loanEntity);

//            entitymanager.clear();


        }


        return null;
    }
}
