package com.example.projetgl;

import androidx.annotation.NonNull;

public class Comment {
    private String comment;
    private String publisher;
    private String id;
    private String publisherid;

    public Comment() {
    }

    public Comment(String comment, String publisher, String id, String publisherid) {
        this.comment = comment;
        this.publisher = publisher;
        this.id = id;
        this.publisherid = publisherid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(String publisherid) {
        this.publisherid = publisherid;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}

