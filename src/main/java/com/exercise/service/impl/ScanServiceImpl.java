package com.exercise.service.impl;

import com.exercise.converter.RenderingConverter;
import com.exercise.domain.Report;
import com.exercise.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScanServiceImpl implements ScanService {

	@Autowired
	private RenderingConverter converter;

	@Override
	public Report scan(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		List<String> lines = null;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return converter.convert(lines);
	}
}
