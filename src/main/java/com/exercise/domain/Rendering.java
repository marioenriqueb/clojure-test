package com.exercise.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 25/04/2019.
 */
public class Rendering implements Serializable {

    private Long document;
    private Integer page;
    private String uid;
    private List<String> starts;
    private List<String> gets;

    public Rendering() {
        this.starts = new ArrayList<>();
        this.gets = new ArrayList<>();
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
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
}
