package com.exercise.service.impl;

import com.exercise.converter.RenderingConverter;
import com.exercise.domain.Report;
import com.exercise.exception.ScanException;
import com.exercise.service.ScanService;
import com.exercise.utils.WriterUtils;
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
	public File scan(MultipartFile multipartFile) throws ScanException {

		FileInputStream fis = null;
		try {
			fis = (FileInputStream) multipartFile.getInputStream();
		} catch (IOException e) {
			throw new ScanException("Can't read the input file " + multipartFile.getOriginalFilename() + ".");
		}

		List<String> lines = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
			lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		Report report = converter.convert(lines);

		return WriterUtils.writeReport(report, multipartFile.getOriginalFilename());
	}
}
