package com.exercise.utils;

import com.exercise.domain.LogRecord;
import com.exercise.domain.RenderingId;
import com.exercise.domain.enums.LogLevel;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogReaderUtils {

    // YYYY-MM-DD HH:mm:ss,SSS
    public final static String TIMESTAMP_FORMAT = "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3})";
    public final static String THREAD_FORMAT = "(\\[[a-zA-Z0-9\\-]+\\])";
    public final static String LOG_LEVEL_FORMAT = "(DEBUG|INFO |WARN|ERROR)";
    public final static String SENDER_FORMAT = "(\\[[a-zA-Z]+\\]:)";
    public final static String MESSAGE_FORMAT = "(.*)";
    public final static String START_RENDERING_FORMAT = "Executing request startRendering with arguments (\\[(\\d+), (\\d+)\\]) on service object \\{(.*)\\}";
    public final static String RENDERING_UID_FORMAT = "Service startRendering returned (\\d{13}-\\d{3,4})";
    public final static String GET_RENDERING_FORMAT = "Executing request getRendering with arguments \\[(\\d{13}-\\d{3,4})\\] on service object \\{(.*)\\}";
    public final static String RENDERING_ARGS_FORMAT = "\\[(\\d+), (\\d+)\\]";
    public final static String UID_FORMAT = "\\d{13}-\\d{3,4}";

    // 2010-10-06 09:06:47,074 [WorkerThread-19] INFO  [ServerSession]: Processing command object: {arguments=[114275, 0], type=request, name=startRendering}
    public final static Pattern LOG_PATTERN = Pattern.compile(TIMESTAMP_FORMAT + " " + THREAD_FORMAT + " " + LOG_LEVEL_FORMAT + " " + SENDER_FORMAT + " " + MESSAGE_FORMAT);

    public final static Pattern START_RENDERING_PATTERN = Pattern.compile(START_RENDERING_FORMAT);
    public final static Pattern RENDERING_UID_PATTERN = Pattern.compile(RENDERING_UID_FORMAT);
    public final static Pattern GET_RENDERING_PATTERN = Pattern.compile(GET_RENDERING_FORMAT);
    public final static Pattern RENDERING_ARGS_PATTERN = Pattern.compile(RENDERING_ARGS_FORMAT);
    public final static Pattern UID_PATTERN = Pattern.compile(UID_FORMAT);

    public final static Optional<LogRecord> readLogLine(String line) {
        Matcher matcher = LOG_PATTERN.matcher(line);
        if (matcher.find()) {
            LogRecord log = new LogRecord();
            log.setTimestamp(matcher.group(1));
            log.setThread(matcher.group(2));
            log.setLevel(matcher.group(3).trim());
            log.setSender(matcher.group(4));
            log.setMessage(matcher.group(5));

            if (LogLevel.INFO.name().equalsIgnoreCase(log.getLevel())) {
                return Optional.of(log);
            }
        }

        return Optional.empty();
    }

    public static Boolean matchStartRenderingMessage(String message) {
        return START_RENDERING_PATTERN.matcher(message).matches();
    }

    public static RenderingId readStartRenderingMessage(String message) {
        Matcher srMatcher = START_RENDERING_PATTERN.matcher(message);
        if (srMatcher.find()) {
            String args = srMatcher.group(1);

            Matcher argsMatcher = RENDERING_ARGS_PATTERN.matcher(args);
            if (argsMatcher.find()) {
                Long document = Long.valueOf(argsMatcher.group(1));
                Integer page = Integer.valueOf(argsMatcher.group(2));

                return new RenderingId(document, page);
            }
        }

        return null;
    }

    public static Boolean matchRenderingUIDMessage(String message) {
        return RENDERING_UID_PATTERN.matcher(message).matches();
    }

    public static String readRenderingUIDMessage(String message) {
        Matcher srMatcher = RENDERING_UID_PATTERN.matcher(message);
        if (srMatcher.find()) {
            return srMatcher.group(1);
        }

        return null;
    }

    public static Boolean matchGetRenderingMessage(String message) {
        return GET_RENDERING_PATTERN.matcher(message).matches();
    }

    public static String readGetRenderingMessage(String message) {
        Matcher srMatcher = GET_RENDERING_PATTERN.matcher(message);
        if (srMatcher.find()) {
            return srMatcher.group(1);
        }

        return null;
    }
}
