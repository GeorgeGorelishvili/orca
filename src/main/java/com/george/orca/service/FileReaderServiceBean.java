package com.george.orca.service;

import com.george.orca.config.FileConfig;
import com.george.orca.domain.*;
import com.george.orca.dto.ExcelRowDTO;
import com.george.orca.repository.OrganizationRepository;
import com.george.orca.repository.PersonRepository;
import com.george.orca.utils.ExcelParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    private final OrganizationService organizationService;
    private final EmployeeService employeeService;
    private final LoanPaymentService loanPaymentService;

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
        ExcelParser excelParser = new ExcelParser(file);


        List<ExcelRowDTO> rowData = excelParser.getPersons();
        for (ExcelRowDTO record : rowData) {

            LoanPaymentEntity payment = new LoanPaymentEntity();


//            OrganizationEntity orgEntity = new OrganizationEntity();
//
//            orgEntity.setOrgName(record.getOrganization());
            //მისამართები
//
//            orgEntity.setPhysicalAddress(record.getPhysicalAddress());
//            orgEntity.setLegalAddress(record.getLegalAddress());
//
//            orgEntity.setDirector(record.getDirector());
//            orgEntity.setCadastrialCode(record.getIdentificationCode());
//
//
//            organizationService.edit(orgEntity);
//
//            organizationRepository.save(orgEntity);
//
//            LoanEntity loanEntity = new LoanEntity();
//
//            loanEntity.setAmount(record.getAmount());
//
//            Long personId = personEntity.getId();
//
//            loanEntity.setDebtorOrganization(orgEntity);
//
//debtor
//            long creditorOrganization = 7;
//
//            OrganizationEntity creditorOrg = new OrganizationEntity();
//            creditorOrg =  organizationService.get(creditorOrganization);
//
//            loanEntity.setCreditorOrganization(creditorOrg);
//            loanEntity.setId(record.getLoanId());
//            loanEntity.setStartDate(record.getStartDate());
//            loanEntity.setIncomeDate(record.getIncomeDate());

            payment.setLoanId(record.getId());
            payment.setAmount(BigDecimal.valueOf(record.getAmount()));
            payment.setComment(record.getComment());
            payment.setDate(record.getDate());

            payment.setPayed(true);


            MathContext mc = new MathContext(10);

            BigDecimal paymentAmount = payment.getAmount();
            LoanEntity loan = loanService.get(record.getId());
            BigDecimal oldAmount = loan.getAmount();


            BigDecimal newLoanAmount = oldAmount.subtract(paymentAmount, mc);

            loan.setAmount(newLoanAmount);
            loanService.edit(loan);

            loanPaymentService.edit(payment);
        }


        return null;
    }
}
