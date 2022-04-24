package com.george.orca.controller;

import com.george.orca.service.FileReaderService;
import com.george.orca.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("file")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService fileService;

    private final FileReaderService fileReaderService;

    @GetMapping("check")
    public ResponseEntity<String> check() {
        String filename = "grafikebi.xlsx";
        fileReaderService.readFile(filename);
        return ResponseEntity.ok("read");
    }

    @PostMapping("upload")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();
            log.info("upload file size: " + bytes.length);
            String name = file.getOriginalFilename();
            log.info("file name: " + name);

            fileService.readFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("file read");
    }

    @GetMapping("read")
    public ResponseEntity<String> readFileFromResource(@RequestParam String filename) {

        fileService.readFileFromResource(filename);
        return ResponseEntity.ok("reeded!");
    }
}
