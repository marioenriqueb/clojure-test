package com.exercise.utils;

import com.exercise.domain.LogRecord;
import com.exercise.domain.RenderingId;
import com.exercise.domain.Report;
import com.exercise.domain.enums.LogLevel;
import com.exercise.exception.ScanException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WriterUtils {

    public static File writeReport(Report report, String fileName) throws ScanException {
        String name = fileName.replace(".", "_");
        name = name + ".xml";
        File file = new File(name);

        file.delete();

        try {
            // Create the file
            file.createNewFile();

            //Write Content
            FileWriter writer = new FileWriter(file);
            writer.write(report.toString());
            writer.close();
        } catch (IOException e) {
            throw new ScanException("Can`t write the result file " + fileName + ".");
        }



        return file;
    }
}
