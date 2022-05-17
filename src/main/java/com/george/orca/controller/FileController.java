package com.george.orca.controller;

import com.george.orca.domain.LoanEntity;
import com.george.orca.service.FileReaderService;
import com.george.orca.service.FileService;
import com.george.orca.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("file")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    @Autowired
    private HttpServletRequest request;
    private final FileService fileService;
    private final LoanService loanService;

    private final FileReaderService fileReaderService;

    @GetMapping("check")
    public ResponseEntity<String> check() {
        String filename = "test.xlsx";
        File file = fileReaderService.readFile(filename);
        return ResponseEntity.ok("checked");
    }

    @GetMapping("upload/list")
    @CrossOrigin
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
    @CrossOrigin
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile,
                                             @RequestParam("id") Long id) {
        fileService.uploadFile(multipartFile, id);
        LoanEntity loanEntity;
        loanEntity = loanService.get(id);
        String AttachedFile = loanEntity.getAttachedFile();
        return ResponseEntity.ok(AttachedFile);
    }

    @GetMapping("read")
    public ResponseEntity<String> readFileFromResource(@RequestParam String filename) {

        fileService.readFileFromResource(filename);
        return ResponseEntity.ok("read!");
    }

    @RequestMapping(path = "/download/loanAttachment", method = RequestMethod.GET)
    public ResponseEntity<Resource> get(@RequestParam String filename) {


        Resource resource = fileService.downloadLoanAttachment(filename);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        HttpHeaders header = new HttpHeaders();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
