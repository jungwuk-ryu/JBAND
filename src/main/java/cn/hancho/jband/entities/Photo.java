package cn.hancho.jband.entities;

import org.json.simple.JSONObject;

public class Photo {
    private String photoAlbumKey, url;
    private User author;
    private long width, height, commentCount, emotionCount, createdAt;
    private boolean isVideoThumbnail;

    public Photo(JSONObject jsonObject){
        if(jsonObject.get("width") != null){
            this.setWidth((Long) jsonObject.get("width"));
            this.setHeight((Long) jsonObject.get("height"));
        }
        if(jsonObject.get("comment_count") != null){
            this.setCommentCount((Long) jsonObject.get("comment_count"));
        }
        if(jsonObject.get("emotion_count") != null){
            this.setEmotionCount((Long) jsonObject.get("emotion_count"));
        }
        if(jsonObject.get("created_at") != null){
            this.setCreatedAt((Long) jsonObject.get("created_at"));
        }
        if(jsonObject.get("is_video_thumbnail") != null){
            this.setIsVideoThumbnail((Boolean) jsonObject.get("is_video_thumbnail"));
        }
        this.setAuthor(new User((JSONObject) jsonObject.get("author")));
        this.setPhotoAlbumKey((String) jsonObject.get("photo_album_key"));  //!
        this.setUrl((String) jsonObject.get("url"));    //!
    }

    public String getPhotoAlbumKey() {
        return photoAlbumKey;
    }

    public void setPhotoAlbumKey(String photoAlbumKey) {
        this.photoAlbumKey = photoAlbumKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
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

    public boolean isVideoThumbnail() {
        return isVideoThumbnail;
    }

    public void setIsVideoThumbnail(boolean videoThumbnail) {
        isVideoThumbnail = videoThumbnail;
    }
}
