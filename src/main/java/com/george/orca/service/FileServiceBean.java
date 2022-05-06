package com.george.orca.service;

import com.george.orca.config.FileConfig;
import com.george.orca.domain.LoanEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceBean implements FileService {

    private final FileConfig fileConfig;

    private final FileReaderService fileReaderService;

    private final LoanService loanService;

    @Override
    public List<String> getUploadedFileNames() {
        File uploadFolder = new File(fileConfig.getFolderPath());
        File[] files = uploadFolder.listFiles();
        List<String> uploadFileNames = new ArrayList<>();
        if (Objects.nonNull(files) && files.length > 0) {
            uploadFileNames = new ArrayList<>(files.length);
            for (File file : files) {
                uploadFileNames.add(file.getName());
            }
        }
        return uploadFileNames;
    }

    @Override
    public void uploadFile(MultipartFile multipartFile, Long id) {

        try {
//            int randomValue = new Random().nextInt();
//            if (randomValue < 0) {
//                randomValue = randomValue * -1;
//            }

            String originalName = multipartFile.getOriginalFilename();

            String[] names = originalName.split("\\.");
            String filename = id + "." + names[1];

            String filePath = fileConfig.getFolderPath() + "/" + filename;
            LoanEntity loanEntity = loanService.get(id);
            loanEntity.setAttachedFile(filename);
            File file = new File(filePath);

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
            loanService.edit(loanEntity);


        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

    }

    @Override
    public void readFileFromResource(String filename) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(filename);

        try {
            if (Objects.nonNull(in)) {
                byte[] bytes = in.readAllBytes();
                log.info("file size: " + bytes.length);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
