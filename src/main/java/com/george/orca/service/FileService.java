package com.george.orca.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    void uploadFile(MultipartFile multipartFile);

    void readFileFromResource(String filename);

    List<String> getUploadedFileNames();
}
