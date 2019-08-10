package com.exercise.web.rest;

import com.exercise.domain.Report;
import com.exercise.service.ScanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Rovert.
 */
@RestController
@RequestMapping("/api/scan")
public class ScanResource {

    private final Logger log = LoggerFactory.getLogger(ScanResource.class);

    @Autowired
    private ScanService service;

    @PostMapping
    public ResponseEntity<String> scan() {
        log.debug("REST request to scan log file : {}");
        Report reporte = service.scan("server.log");
        return ResponseEntity.status(HttpStatus.CREATED).body(reporte.toString());
    }

}
