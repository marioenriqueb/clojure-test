package com.exercise.service;

import com.exercise.exception.ScanException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public interface ScanService {
    File scan(MultipartFile multipartFile) throws ScanException;
}
