package com.exercise.domain;

import java.util.List;

/**
 * Created by mario on 09/08/2019.
 */
public class Report {

    private List<Rendering> renderings;

    public List<Rendering> getRenderings() {
        return renderings;
    }

    public void setRenderings(List<Rendering> renderings) {
        this.renderings = renderings;
    }

    public Integer getCount() {
        return this.renderings.size();
    }

    public Long getDuplicates() {
        return renderings.stream().filter(item -> item.getStarts().size() > 1).count();
    }

    public Long getUnnecessary() {
        return renderings.stream().filter(item -> item.getGets().size() == 0).count();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<report>");
        this.renderings.forEach(rendering -> {
            builder.append(rendering.toString());
        });
        builder.append("    <!-- Summary -->");
        builder.append("    <summary>");
        builder.append("        <!-- Total number of renderings -->");
        builder.append("        <count>" + getCount() + "</count>");
        builder.append("        <!-- Number of double renderings (multiple starts with same UID) -->");
        builder.append("        <duplicates>" + getDuplicates() + "</duplicates>");
        builder.append("        <!-- Number of startRenderings without get -->");
        builder.append("        <unnecessary>" + getUnnecessary() + "</unnecessary>");
        builder.append("    </summary>");
        builder.append("</report>");
        return builder.toString();
    }
}
