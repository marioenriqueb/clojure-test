package com.exercise.converter;

import com.exercise.domain.Rendering;
import com.exercise.domain.RenderingId;
import com.exercise.domain.Report;
import com.exercise.utils.ReaderUtils;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RenderingConverter {

    public Report convert(List<String> lines) {
        Map<String, Rendering> renderings = new HashMap<>();

        lines.stream()
                .map(ReaderUtils::readLogLine)
                .flatMap(optional -> optional.map(Stream::of).orElseGet(Stream::empty))
                .forEach(logRecord -> {
                    String message = logRecord.getMessage();
                    Rendering current = new Rendering();

                    // Start Rendering
                    if (ReaderUtils.matchStartRenderingMessage(message)) {
                        RenderingId renderingId = ReaderUtils.readStartRenderingMessage(message);
                        current.setDocument(renderingId.getDocument());
                        current.setPage(renderingId.getPage());
                    }

                    // UID Recived
                    if(ReaderUtils.matchRenderingUIDMessage(message)) {
                        String uid = ReaderUtils.readRenderingUIDMessage(message);
                        Rendering rendering = renderings.get(uid);
                        if (rendering != null) {
                            rendering.addStart(logRecord.getTimestamp());
                        } else {
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
                });

        return generateReport(renderings);
    }

    private Report generateReport(Map<String, Rendering> renderings) {
        Report report = new Report();
        report.setRenderings(renderings.values().stream().collect(Collectors.toList()));
        return report;
    }
}
