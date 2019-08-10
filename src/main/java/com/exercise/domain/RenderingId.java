package com.exercise.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * Created by mario on 25/04/2019.
 */
public class RenderingId implements Serializable {

    private Long document;
    private Integer page;

    public RenderingId(Long document, Integer page) {
        this.document = document;
        this.page = page;
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
}
