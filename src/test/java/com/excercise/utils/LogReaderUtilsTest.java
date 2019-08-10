package com.excercise.utils;

import com.exercise.utils.LogReaderUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mario on 09/08/2019.
 */
public class LogReaderUtilsTest {

    public static final String GROUP1 = "2010-10-06 09:06:47,074";
    public static final String GROUP2 = "[WorkerThread-19]";
    public static final String GROUP3 = "INFO ";
    public static final String GROUP4 = "[ServerSession]:";
    public static final String GROUP5 = "Processing command object: {arguments=[114275, 0], type=request, name=startRendering}";

    @Test
    public void logPatternTest() {
        // Before
        String line = GROUP1 + " " + GROUP2 + " " + GROUP3 + " " + GROUP4 + " " + GROUP5;

        // Test
        Matcher matcher = LogReaderUtils.LOG_PATTERN.matcher(line);
        if (!matcher.find()) {
            Assert.fail();
            Assert.assertEquals(GROUP1, matcher.group(1));
            Assert.assertEquals(GROUP2, matcher.group(2));
            Assert.assertEquals(GROUP3, matcher.group(3));
            Assert.assertEquals(GROUP4, matcher.group(4));
            Assert.assertEquals(GROUP5, matcher.group(5));
        }
    }

    @Test
    public void timestampPatternTest()  {
        // Before
        String line = "2010-10-06 09:06:47,074";
        Pattern pattern = Pattern.compile(LogReaderUtils.TIMESTAMP_FORMAT);

        // Test
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            Assert.fail();
        }
    }

    @Test
    public void threadPatternTest()  {
        // Before
        String[] lines = {"[WorkerThread-19]", "[RenderingQueue]"};
        Pattern pattern = Pattern.compile(LogReaderUtils.THREAD_FORMAT);

        // Test
        Arrays.stream(lines).forEach(line -> {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()) {
                Assert.fail();
            }
        });

        Matcher matcher = pattern.matcher("RenderingQueue");
        if (matcher.find()) {
            Assert.fail();
        }
    }

    @Test
    public void logLevelPatternTest()  {
        // Before
        String[] lines = {"DEBUG", "INFO ", "WARN" , "ERROR"};
        Pattern pattern = Pattern.compile(LogReaderUtils.LOG_LEVEL_FORMAT);

        // Test
        Arrays.stream(lines).forEach(line -> {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()) {
                Assert.fail();
            }
        });

        Matcher matcher = pattern.matcher("PEPE");
        if (matcher.find()) {
            Assert.fail();
        }
    }

    @Test
    public void senderPatternTest()  {
        // Before
        String line = "[DmsObjectDeterminator]:";
        Pattern pattern = Pattern.compile(LogReaderUtils.SENDER_FORMAT);

        // Test
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            Assert.fail();
        }
    }

    @Test
    public void messagePatternTest()  {
        // Before
        String line = "Processing command object: {arguments=[114275, 0], type=request, name=startRendering}";
        Pattern pattern = Pattern.compile(LogReaderUtils.MESSAGE_FORMAT);

        // Test
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            Assert.fail();
        }
    }

    @Test
    public void startRenderingPatternTest()  {
        // Before
        String line = "Executing request startRendering with arguments [115392, 0] on service object { ReflectionServiceObject -> com.dn.gaverzicht.dms.services.DocumentService@4a3a4a3a }";

        // Test
        Matcher matcher = LogReaderUtils.START_RENDERING_PATTERN.matcher(line);
        if (matcher.find()) {
            Assert.assertEquals("[115392, 0]", matcher.group(1));
        } else {
            Assert.fail();
        }
    }

    @Test
    public void renderingUIDPatternTest()  {
        // Before
        String[] lines = {"Service startRendering returned 1286374050308-1583", "Service startRendering returned 1286374050308-183"};

        // Test
        Arrays.stream(lines).forEach(line -> {
            Matcher matcher = LogReaderUtils.RENDERING_UID_PATTERN.matcher(line);
            if (!matcher.find()) {
                Assert.fail();
            }
        });

        String line = "Service startRendering returned 1286374050308-1583";

        // Test
        Matcher matcher = LogReaderUtils.RENDERING_UID_PATTERN.matcher(line);
        if (matcher.find()) {
            Assert.assertEquals("1286374050308-1583", matcher.group(1));
        } else {
            Assert.fail();
        }
    }

    @Test
    public void getRenderingPatternTest()  {
        // Before
        String line = "Executing request getRendering with arguments [1286375469143-4450] on service object { ReflectionServiceObject -> com.dn.gaverzicht.dms.services.DocumentService@4a3a4a3a }";

        // Test
        Matcher matcher = LogReaderUtils.GET_RENDERING_PATTERN.matcher(line);
        if (matcher.find()) {
            Assert.assertEquals("1286375469143-4450", matcher.group(1));
        } else {
            Assert.fail();
        }
    }

    @Test
    public void renderingArgsPatternTest()  {
        // Before
        String line = "[115392, 0]";

        // Test
        Matcher matcher = LogReaderUtils.RENDERING_ARGS_PATTERN.matcher(line);
        if (matcher.find()) {
            Assert.assertEquals("115392", matcher.group(1));
            Assert.assertEquals("0", matcher.group(2));
        } else {
            Assert.fail();
        }
    }

    @Test
    public void uidPatternTest()  {
        // Before
        String line = "1286375469143-4450";

        // Test
        Matcher matcher = LogReaderUtils.UID_PATTERN.matcher(line);
        if (matcher.find()) {
            Assert.assertEquals("1286375469143-4450", matcher.group(0));
        } else {
            Assert.fail();
        }
    }
}
