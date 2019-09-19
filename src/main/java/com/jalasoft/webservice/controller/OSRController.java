package com.jalasoft.webservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping ("/api/v1.0/ocr")
public class OSRController {

    @PostMapping
    public String ocrext(@RequestParam("file") MultipartFile file, @RequestParam(value = "lang", defaultValue = "") String lang) {
        try {
            String filePath = "/home/itadmin/Downloads/" + file.getOriginalFilename();
            Path location = Paths.get(filePath);

            Files.copy(file.getInputStream(), location);
        } catch (IOException e) {
            System.out.println("Error");
        }
       return "Done";
    }
}



