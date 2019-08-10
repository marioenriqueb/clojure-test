package com.excercise.converter;

import com.excercise.BaseTest;
import com.exercise.converter.RenderingConverter;
import com.exercise.domain.Rendering;
import com.exercise.domain.Report;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mario on 09/08/2019.
 */
public class RenderingConverterTest extends BaseTest {

    @Autowired
    private RenderingConverter conveter;

    @Test
    public void createTest() throws IOException {
        // Before
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("file/server.log").getFile());

        // Test
        List<String> lines = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            lines = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Report result = conveter.convert(lines);
        Assert.assertEquals(Integer.valueOf(859), result.getCount());
        Assert.assertEquals(Long.valueOf(198), result.getUnnecessary());
        Assert.assertEquals(Long.valueOf(1), result.getDuplicates());
    }
}
