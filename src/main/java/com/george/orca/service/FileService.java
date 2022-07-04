package com.george.orca.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    void uploadFile(MultipartFile multipartFile, Long id, String name);

    void readFileFromResource(String filename);

    List<String> getUploadedFileNames();

    Resource downloadLoanAttachment(String filename);
}
