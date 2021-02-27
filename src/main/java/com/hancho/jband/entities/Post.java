package com.hancho.jband.entities;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

@Getter
@Setter
public class Post {
    private String bandKey, postKey, content;
    private long commentCount, emotionCount, createdAt, view;
    private ArrayList<Comment> latestComments, comments;
    private ArrayList<Photo> photos;
    private User author;

    public Post(JSONObject jsonObject){
        this.setContent((String) jsonObject.get("content")); //이스케이프 처리 필요
        this.setPostKey((String) jsonObject.get("post_key"));
        this.setBandKey((String) jsonObject.get("band_key"));
        this.setCommentCount((long) jsonObject.get("comment_count"));
        this.setEmotionCount((long) jsonObject.get("emotion_count"));
        this.setCreatedAt((long) jsonObject.get("created_at"));
        if(jsonObject.get("post_read_count") != null){
            this.setView((Long) jsonObject.get("post_read_count"));
        }else{
            this.setView(-1);
        }

        JSONObject jsonAuthor = (JSONObject) jsonObject.get("author");
        User author = null;
        if(jsonAuthor != null){
            author = new User(jsonAuthor);
        }
        this.setAuthor(author);
        //latest, photos

        JSONArray jsonLatestComments = (JSONArray) jsonObject.get("latest_comments");
        ArrayList<Comment> latestComments = null;
        if(jsonLatestComments != null) {
            latestComments = new ArrayList<>();
            for (Object obj : jsonLatestComments) {
                JSONObject jsonComment = (JSONObject) obj;
                latestComments.add(new Comment(jsonComment));
            }
        }
        this.setLatestComments(latestComments);

        JSONArray jsonPhotos = (JSONArray) jsonObject.get("photos");
        ArrayList<Photo> photos = null;
        if(jsonPhotos != null){
            photos = new ArrayList<>();
            for(Object obj : jsonPhotos){
                JSONObject jsonPhoto = (JSONObject) obj;
                photos.add(new Photo(jsonPhoto));
            }
        }
        this.setPhotos(photos);
    }

    public void getComments(){

    }

    public void writeComment(){

    }

    public void removeComment(){

    }
}
