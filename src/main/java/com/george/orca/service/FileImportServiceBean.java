package com.george.orca.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileImportServiceBean implements FileService {

    private final FileReaderService fileReaderService;

    @Override
    public void readFile(MultipartFile multipartFile) {

        try {
            String filename = multipartFile.getOriginalFilename();
            log.info("reading file: " + filename);

//            log.info("upload folder: " + uploadFolderPath);
//            String filename = "grafikebi.xlsx";
            File file = fileReaderService.readFile(filename);
            FileInputStream fis = new FileInputStream(file);


        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

    }

    @Override
    public void readFileFromResource(String filename) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(filename);

        try {
            byte[] bytes = in.readAllBytes();
            log.info("file size: " + bytes.length);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
