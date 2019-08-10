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
        builder.append("<report>" + System.lineSeparator());
        this.renderings.forEach(rendering -> {
            builder.append(rendering.toString());
        });
        builder.append("    <!-- Summary -->" + System.lineSeparator());
        builder.append("    <summary>" + System.lineSeparator());
        builder.append("        <!-- Total number of renderings -->" + System.lineSeparator());
        builder.append("        <count>" + getCount() + "</count>" + System.lineSeparator());
        builder.append("        <!-- Number of double renderings (multiple starts with same UID) -->" + System.lineSeparator());
        builder.append("        <duplicates>" + getDuplicates() + "</duplicates>" + System.lineSeparator());
        builder.append("        <!-- Number of startRenderings without get -->" + System.lineSeparator());
        builder.append("        <unnecessary>" + getUnnecessary() + "</unnecessary>" + System.lineSeparator());
        builder.append("    </summary>" + System.lineSeparator());
        builder.append("</report>" + System.lineSeparator());
        return builder.toString();
    }
}
