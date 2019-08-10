package com.exercise.web.rest;

import com.exercise.domain.Report;
import com.exercise.service.ScanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.nio.file.Path;

/**
 * REST controller for managing Rovert.
 */
@RestController
@RequestMapping("/api")
public class ScanResource {

    private final Logger log = LoggerFactory.getLogger(ScanResource.class);

    @Autowired
    private ScanService service;

    @PostMapping(value = "/scan", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    //@PostMapping(name = "/scan", produces = MediaType.TEXT_XML_VALUE)
    //@ResponseBody
    public ResponseEntity<Resource> scan(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        log.debug("REST request to scan log file : {}");

        FileInputStream file = (FileInputStream) multipartFile.getInputStream();
        Report reporte = service.scan(file);

        String fileName = multipartFile.getOriginalFilename().replace("log", "xml");
        File result = new File(fileName);

        // Create the file
        result.delete();
        result.createNewFile();

        //Write Content
        FileWriter writer = new FileWriter(result);
        writer.write(reporte.toString());
        writer.close();

        Path filePath = result.toPath();
        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

}
