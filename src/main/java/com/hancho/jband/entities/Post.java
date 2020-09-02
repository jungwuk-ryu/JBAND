package com.hancho.jband.entities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

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

    public String getBandKey() {
        return bandKey;
    }

    public void setBandKey(String bandKey) {
        this.bandKey = bandKey;
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public long getEmotionCount() {
        return emotionCount;
    }

    public void setEmotionCount(long emotionCount) {
        this.emotionCount = emotionCount;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public ArrayList<Comment> getLatestComments() {
        return latestComments;
    }

    public void setLatestComments(ArrayList<Comment> latestComments) {
        this.latestComments = latestComments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }
}
