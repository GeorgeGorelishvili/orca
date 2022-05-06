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
import java.util.List;

@RestController
@RequestMapping("file")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService fileService;

    private final FileReaderService fileReaderService;

    @GetMapping("check")
    public ResponseEntity<String> check() {
        String filename = "test.xlsx";
        File file = fileReaderService.readFile(filename);
        return ResponseEntity.ok("checked");
    }

    @GetMapping("upload/list")
    public ResponseEntity<List<String>> uploadFileList() {
        List<String> fileNames = fileService.getUploadedFileNames();
        return ResponseEntity.ok(fileNames);
    }

    @PostMapping("example")
    public ResponseEntity<String> example(@RequestParam("filename") String filename) {
        File file = fileReaderService.readFile(filename);
        return ResponseEntity.ok("grafikebi.xlsx file read!");
    }

    @PostMapping(value = "upload/loanfile", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile,
                                             @RequestParam("id") Long id) {
        fileService.uploadFile(multipartFile, id);
        return ResponseEntity.ok("file read");
    }

    @GetMapping("read")
    public ResponseEntity<String> readFileFromResource(@RequestParam String filename) {

        fileService.readFileFromResource(filename);
        return ResponseEntity.ok("read!");
    }
}
