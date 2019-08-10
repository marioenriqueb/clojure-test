package com.exercise.converter;

import com.exercise.domain.LogRecord;
import com.exercise.domain.Rendering;
import com.exercise.utils.ReaderUtils;
import com.exercise.domain.RenderingId;
import com.exercise.domain.Report;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RenderingConverter {

    public final String CURRENT = "CURRENT";

    public Report convert(List<String> lines) {
        Map<String, Rendering> renderings = new HashMap<>();
        lines.forEach(line -> {
            Optional<LogRecord> response = ReaderUtils.readLogLine(line);
            if (response.isPresent()) {
                LogRecord logRecord = response.get();
                String message = logRecord.getMessage();
                // Start Rendering
                if (ReaderUtils.matchStartRenderingMessage(message)) {
                    RenderingId renderingId = ReaderUtils.readStartRenderingMessage(message);

                    if (renderings.get(renderingId) == null) {
                        Rendering rendering = new Rendering();
                        rendering.setId(renderingId);
                        renderings.put(CURRENT, rendering);
                    }
                }

                // UID Recived
                if(ReaderUtils.matchRenderingUIDMessage(message)) {
                    String uid = ReaderUtils.readRenderingUIDMessage(message);
                    Rendering current = renderings.get(CURRENT);
                    if (current != null){
                        current.setUid(uid);
                        current.addStart(logRecord.getTimestamp());
                        renderings.put(uid, current);
                    }
                }

                // Get Rendering
                if(ReaderUtils.matchGetRenderingMessage(message)) {
                    String uid = ReaderUtils.readGetRenderingMessage(message);
                    if (renderings.get(uid) != null) {
                        renderings.get(uid).addGet(logRecord.getTimestamp());
                    }
                }
            }
        });

        return generateReport(renderings);
    }

    private Report generateReport(Map<String, Rendering> renderings) {
        Report report = new Report();
        report.setRenderings(renderings.values().stream().collect(Collectors.toList()));
        return report;
    }
}
