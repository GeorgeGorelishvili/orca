package com.george.orca.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    void readFile(MultipartFile multipartFile);

    void readFileFromResource(String filename);
}
