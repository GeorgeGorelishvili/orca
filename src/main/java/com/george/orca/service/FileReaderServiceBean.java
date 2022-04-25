package com.george.orca.service;

import com.george.orca.config.FileConfig;
import com.george.orca.domain.PersonEntity;
import com.george.orca.utils.ExcelParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileReaderServiceBean implements FileReaderService {

    private final FileConfig fileConfig;

    @Override
    public File readFile(String filename) {
        log.info("reading filename: " + filename);
        log.info("file upload folder path: " + fileConfig.getFolderPath());
        log.info("file upload max size: " + fileConfig.getFileSize());

        String filePath = fileConfig.getFolderPath();
        if (!filePath.endsWith("/")) {
            filePath += "/";
        }
        filePath += filename;
        log.info("reading file: " + filePath);

        File file = new File(filePath);
        ExcelParser excelParser = new ExcelParser(file);
        Map<Integer, String> headers = excelParser.getHeaderMap();
        log.info("print headers: ");
        for (Map.Entry<Integer, String> entry : headers.entrySet()) {
            String headerCellValue = entry.getValue();
            log.info("header index: " + entry.getKey() + " " + "header value: " + headerCellValue);
        }

        log.info("");
        log.info("პიროვნებები");
        List<PersonEntity> personEntities = excelParser.getPersons();
        for (PersonEntity personEntity : personEntities) {
            log.info("Person name: " + personEntity.getFirstname() + " lastname: " + personEntity.getLastname());
        }

        /*try {
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<Row> rowIterator = mySheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    log.info("cell: " + cell.getStringCellValue());
                    *//*switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        default :
                    }*//*
                }
                log.info("");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return file;
    }

}
