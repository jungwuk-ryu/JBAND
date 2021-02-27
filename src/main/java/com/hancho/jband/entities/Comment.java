package com.hancho.jband.entities;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Getter
@Setter
public class Comment {
    private User author;
    private long createdAt;
    private String content;

    public Comment(JSONObject jsonObject){
        this.setAuthor(new User((JSONObject) jsonObject.get("author")));
        this.setContent((String) jsonObject.get("body"));
        this.setCreatedAt((Long) jsonObject.get("created_at"));
    }
}
