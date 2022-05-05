package com.george.orca.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    void uploadFile(MultipartFile multipartFile, String id);

    void readFileFromResource(String filename);

    List<String> getUploadedFileNames();
}
