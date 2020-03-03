package com.sidekick.pixogram.mediaservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    private final Path rootLocation = Paths.get("mediaservice/src/main/resources/static").toAbsolutePath();
    public void store(MultipartFile file) {
        try {
            /*
             create a new directory
             File file_new = new File(this.rootLocation.getName(0).toString() + "/" + "");
             file_new.mkdir();
            */
            System.out.println(rootLocation.resolve(file.getOriginalFilename()));
           Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));


        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("FAIL!");
        }
    }

}
