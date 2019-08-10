package com.exercise.service;

import com.exercise.domain.Report;

import java.io.FileInputStream;

public interface ScanService {
    Report scan(FileInputStream file);
}
