package com.exercise.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 25/04/2019.
 */
public class Rendering implements Serializable {

    private RenderingId id;
    private String uid;
    private List<String> starts;
    private List<String> gets;

    public Rendering() {
        this.starts = new ArrayList<>();
        this.gets = new ArrayList<>();
    }

    public RenderingId getId() {
        return id;
    }

    public void setId(RenderingId id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<String> getStarts() {
        return starts;
    }

    public void setStarts(List<String> starts) {
        this.starts = starts;
    }

    public void addStart(String newStart) {
        this.starts.add(newStart);
    }

    public List<String> getGets() {
        return gets;
    }

    public void setGets(List<String> gets) {
        this.gets = gets;
    }

    public void addGet(String newGet) {
        this.gets.add(newGet);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("    <rendering>" + System.lineSeparator());
        builder.append("        <document>" + this.id.getDocument() + "</document>" + System.lineSeparator());
        builder.append("        <page>" + this.id.getPage() + "</page>" + System.lineSeparator());
        builder.append("        <uid>" + this.getUid() + "</uid>" + System.lineSeparator());
        this.getStarts().forEach(start -> {
            builder.append("        <start>" + start + "</start>" + System.lineSeparator());
        });
        this.getGets().forEach(get -> {
            builder.append("        <get>" + get + "</get>" + System.lineSeparator());
        });
        builder.append("    </rendering>" + System.lineSeparator());
        return builder.toString();
    }
}
