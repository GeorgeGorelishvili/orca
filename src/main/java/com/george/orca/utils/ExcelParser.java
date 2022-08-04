package com.george.orca.utils;

import com.george.orca.domain.*;
import com.george.orca.dto.ExcelRowDTO;
import com.george.orca.service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.george.orca.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelParser {


    private static final Logger logger = LoggerFactory.getLogger(ExcelParser.class);
    private File file;
    private int sheetIndex;

    // local variables
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelParser(File file) {
        this(file, 0);
        logger.info("creating ExcelParser class");
    }

    public ExcelParser(File file, Integer sheetIndex) {
        this.file = file;
        if (Objects.nonNull(sheetIndex)) {
            this.sheetIndex = sheetIndex;
        } else {
            this.sheetIndex = 0;
        }
        workbook = createWorkbook();
        sheet = createSheet();
    }

    private XSSFWorkbook createWorkbook() {
        try {
            FileInputStream fis = new FileInputStream(file);
            this.workbook = new XSSFWorkbook(fis);
        } catch (FileNotFoundException fnfe) {
            logger.error(fnfe.getMessage(), fnfe);
        } catch (IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
        }
        return workbook;
    }

    private XSSFSheet createSheet() {
        return workbook.getSheetAt(sheetIndex);
    }

//    public Map<Integer, String> getHeaderMap() {
//        Iterator<Row> headerIterator = sheet.iterator();
//        Row row = headerIterator.next();
//
//        Map<Integer, String> headerMap = new HashMap<>();
//        int cellIndex = 0;
//        Iterator<Cell> cellIterator = row.cellIterator();
//        while (cellIterator.hasNext()) {
//            Cell cell = cellIterator.next();
//            String cellValue = cell.getStringCellValue();
//            headerMap.put(cellIndex++, cellValue);
//        }
//        return headerMap;
//    }

    public List<ExcelRowDTO> getPersons() {
        Iterator<Row> rowIterator = sheet.iterator();
        Row row = rowIterator.next();
        List<ExcelRowDTO> rowData = new ArrayList<>();

        for (Row myrow : sheet) {
            ExcelRowDTO rowDTO = new ExcelRowDTO();


//            rowDTO.setOrgName(myrow.getCell(1).getStringCellValue());
//            rowDTO.setDirector(myrow.getCell(4).getStringCellValue());

            long creditorOrganization = new Double(myrow.getCell(0).getNumericCellValue()).longValue();
            rowDTO.setCreditorOrganizationId(creditorOrganization);

//
//            rowDTO.setLoanId(loanId);

            //მისამართები
            rowDTO.setLegalAddress(myrow.getCell(5).getStringCellValue());
            rowDTO.setPhysicalAddress(myrow.getCell(6).getStringCellValue());


            //ნებისმიერი ცვლადი სტრინგჰში
            // პირადი ნომერი
            // ს/კ
            DataFormatter formatter = new DataFormatter();
            String name = formatter.formatCellValue(myrow.getCell(1));

            rowDTO.setFirstname(name);

            String lastname = formatter.formatCellValue(myrow.getCell(2));

            String personalNumber = formatter.formatCellValue(myrow.getCell(3));

            rowDTO.setLastname(lastname);
            rowDTO.setPersonalNumber(personalNumber);


            String cont1 = formatter.formatCellValue(myrow.getCell(7));
            String cont2 = formatter.formatCellValue(myrow.getCell(8));

            rowDTO.setContact(cont1 + " - " + cont2);

            //ნუმერიკი ბიგინტში

//            long loanId = new Double(myrow.getCell(0).getNumericCellValue()).longValue();


//            rowDTO.setLoanId(loanId);


            // 5 შემოსვლის
            // 6 დაწყების

            String startDate = formatter.formatCellValue(myrow.getCell(9));
            String incomeDate = formatter.formatCellValue(myrow.getCell(10));


            Date convertedIncomeDate;
            Date convertedStartDate;

            try {
                convertedStartDate = new SimpleDateFormat("MM/dd/yyyy").parse(startDate);
                convertedIncomeDate = new SimpleDateFormat("dd/MM/yyyy").parse(incomeDate);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            rowDTO.setIncomeDate(convertedIncomeDate);
            rowDTO.setStartDate(convertedStartDate);
//
//
            String amountToConvert = formatter.formatCellValue(myrow.getCell(4));
            BigDecimal amountToBigDecimal = new BigDecimal(amountToConvert);
            rowDTO.setAmount(amountToBigDecimal);

//            try {
//                //  Block of code to try to write to cell
//                rowDTO.setPhone(myrow.getCell(5).getStringCellValue());
//            }
//            catch(Exception e) {
//
//                //  Block of code to handle errors and crate cell and write to it
//                rowDTO.setPhone(" ");
//            }


//            DataFormatter formatter = new DataFormatter();


//            long id = new Double(myrow.getCell(0).getNumericCellValue()).longValue();
//            rowDTO.setId(id);


//            String comment = formatter.formatCellValue(myrow.getCell(3));
//            rowDTO.setComment(comment);
//
//            Double amountToConvert = new BigDecimal(myrow.getCell(2).getNumericCellValue()).doubleValue();
//
////            BigDecimal amountToBigDecimal = new BigDecimal(amountToConvert);
//            rowDTO.setAmount(amountToConvert);
//
//            Date date = myrow.getCell(1).getDateCellValue();
//            Date convertedDate;

//            try {
//                convertedDate = new SimpleDateFormat("M.d.yyyy h:mm").parse(date);
//            } catch (ParseException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//            rowDTO.setDate(date);
//

            rowData.add(rowDTO);

        }

        return rowData;
    }

}
