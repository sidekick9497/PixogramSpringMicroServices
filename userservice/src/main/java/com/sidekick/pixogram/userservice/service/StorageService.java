package com.sidekick.pixogram.userservice.service;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;


@Service
public class StorageService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private final Path rootLocation = Paths.get("userservice/src/main/resources/static").toAbsolutePath();
    public void store(MultipartFile file) {
        try {

            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("FAIL!");
        }
    }

}