package com.exercise.service.impl;

import com.exercise.converter.RenderingConverter;
import com.exercise.domain.Report;
import com.exercise.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScanServiceImpl implements ScanService {

	@Autowired
	private RenderingConverter converter;

	@Override
	public Report scan(FileInputStream file) {

		List<String> lines = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(file))) {
			lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return converter.convert(lines);
	}
}
