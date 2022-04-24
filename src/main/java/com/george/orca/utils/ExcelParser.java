package com.george.orca.utils;

import com.george.orca.domain.PersonEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public Map<Integer, String> getHeaderMap() {
        Iterator<Row> headerIterator = sheet.iterator();
        Row row = headerIterator.next();

        Map<Integer, String> headerMap = new HashMap<>();
        int cellIndex = 0;
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String cellValue = cell.getStringCellValue();
            headerMap.put(cellIndex++, cellValue);
        }
        return headerMap;
    }

    public List<PersonEntity> getPersons() {
        List<String> names = new ArrayList<>();

        Iterator<Row> rowIterator = sheet.iterator();
        Row row = rowIterator.next();

        List<PersonEntity> persons = new ArrayList<>();
        PersonEntity personEntity;
        logger.error("not valid values ");
        while (rowIterator.hasNext()) {
            // get next line
            row = rowIterator.next();

            Integer cellIndex = 1;
            Cell cell = row.getCell(cellIndex);
            String personName = cell.getStringCellValue();
            personName = personName.trim(); // correct data
            if (!StringUtils.isEmpty(personName)) { // when is not empty
                if (!personName.contains("შპს")) { // when is not შპს
                    String[] name = personName.split(" ");
                    if (name.length == 2) { // when is more then one word
                        personEntity = new PersonEntity();
                        String firstName = name[0];
                        personEntity.setFirstname(firstName.trim());
                        String lastName = name[1];
                        personEntity.setLastname(lastName.trim());
                        persons.add(personEntity);
                    } else {
                        logger.error(personName);
                    }
                }
            }
        }
        return persons;
    }

}
