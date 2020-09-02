package com.hancho.jband.entities;

import org.json.simple.JSONObject;

public class Comment {
    private User author;
    private long createdAt;
    private String content;

    public Comment(JSONObject jsonObject){
        this.setAuthor(new User((JSONObject) jsonObject.get("author")));
        this.setContent((String) jsonObject.get("body"));
        this.setCreatedAt((Long) jsonObject.get("created_at"));
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
